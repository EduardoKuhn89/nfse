package br.com.nfse.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;

public class FileUtils extends org.apache.commons.io.FileUtils {

    public static String toFileNamePath(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        // Normaliza e remove acentos
        str = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        // Remove caracteres especiais, exceto letras, números, espaços e ponto (.)
        str = str.replaceAll("[^a-zA-Z0-9.\\s]", " ");

        // Remove espaços extras
        return str.trim().replaceAll("\\s+", " ");
    }

    public static String sanitizeFileName(String str, boolean rmAccents) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        if (rmAccents) {
            // Normaliza e remove acentos (ex.: "ç" -> "c")
            str = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        }

        // Remove caracteres inválidos para nomes de arquivos/pastas (Windows + Linux)        
        str = str.replaceAll("[\\\\/:*?\"<>|]", "");  // Windows
        str = str.replaceAll("/", "");               // Linux/macOS

        // Remove espaços extras no início/fim e múltiplos espaços
        str = str.trim().replaceAll("\\s+", " ");
        return str;
    }

    public static String getTempDir() {
        String tempPath = System.getProperty("java.io.tmpdir");
        if (!tempPath.endsWith(File.separator)) {
            tempPath = tempPath + File.separator;
        }
        return tempPath;
    }

    public static File createTempPath() {
        String tempPath = System.getProperty("java.io.tmpdir");
        if (!tempPath.endsWith(File.separator)) {
            tempPath = tempPath + File.separator;
        }

        File file = new File(tempPath + UUID.randomUUID().toString());
        file.mkdir();
        return file;
    }

    public static File createTempFile() {
        String tempPath = System.getProperty("java.io.tmpdir");
        if (!tempPath.endsWith(File.separator)) {
            tempPath = tempPath + File.separator;
        }
        return new File(tempPath + UUID.randomUUID().toString());
    }

    public static ArrayList<File> getFilesList(String path) throws Exception {
        File pasta = new File(path);
        if (!pasta.exists()) {
            throw new Exception("A pasta '" + path + "' não existe!");
        }

        ArrayList<File> lista = new ArrayList<>();
        addFiles(pasta, lista);
        return lista;
    }

    public static File[] getListFiles(String path) throws Exception {
        File pasta = new File(path);
        if (!pasta.exists()) {
            throw new Exception("A pasta '" + path + "' não existe!");
        }

        ArrayList<File> lista = new ArrayList<>();
        addFiles(pasta, lista);

        File[] ret = new File[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            ret[i] = lista.get(i).getAbsoluteFile();
        }

        return ret;
    }

    private static void addFiles(File pasta, ArrayList<File> lista) {
        File[] listFiles = pasta.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File f : listFiles) {
                if (f.getAbsoluteFile().isFile()) {
                    lista.add(f);
                } else {
                    addFiles(f, lista);
                }
            }
        }
    }

    public static boolean zipar(String input, String output) {
        String dirInterno = "";
        boolean retorno = true;
        try {
            File file = new File(input);
            if (!file.exists()) {
                return false;
            }
            try (ZipOutputStream zipDestino = new ZipOutputStream(new FileOutputStream(output))) {
                if (file.isFile()) {
                    ziparFile(file, dirInterno, zipDestino);
                } else {
                    dirInterno = file.getName();
                    File[] files = file.listFiles();
                    for (File file1 : files) {
                        ziparFile(file1, dirInterno, zipDestino);
                    }
                }
            }
        } catch (IOException ex) {
            retorno = false;
        }
        return retorno;
    }

    public static void ziparFile(File file, String subDiretorio, ZipOutputStream zipDestino) throws IOException {
        if (subDiretorio == null) {
            subDiretorio = "";
        }
        byte data[] = new byte[2048];
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                ziparFile(file1, (subDiretorio.isEmpty() ? "" : subDiretorio + File.separator) + file.getName(), zipDestino);
            }
            return;
        }
        try (FileInputStream fi = new FileInputStream(file.getAbsolutePath())) {
            ZipEntry entry = new ZipEntry((subDiretorio.isEmpty() ? "" : subDiretorio + File.separator) + file.getName());
            zipDestino.putNextEntry(entry);
            int count;
            while ((count = fi.read(data)) > 0) {
                zipDestino.write(data, 0, count);
            }
            zipDestino.closeEntry();
        }
    }

    public static String fileHash(File file, String algoritmo) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algoritmo);
        try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[8192]; // 8KB de buffer
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }
        return toHexFormat(digest.digest());
    }

    private static String toHexFormat(byte[] bytes) {
        final StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    public static class FileDto {

        private String nomeFile;
        private String extencao;
        private byte[] bytes;

        public String getNomeFile() {
            return nomeFile;
        }

        public void setNomeFile(String nomeFile) {
            this.nomeFile = nomeFile;
        }

        public byte[] getBytes() {
            return bytes;
        }

        public void setBytes(byte[] bytes) {
            this.bytes = bytes;
        }

        public String getExtencao() {
            if (extencao == null) {
                extencao = "";
            }
            return extencao.trim();
        }

        public void setExtencao(String extencao) {
            this.extencao = extencao;
        }
    }

    public static String removeExt(String nomeFile) {
        if (nomeFile == null || !nomeFile.contains(".")) {
            return nomeFile;
        }
        return nomeFile.substring(0, nomeFile.lastIndexOf("."));
    }

    public static InputStream fileToStream(File file) throws Exception {
        return Files.newInputStream(file.toPath());
    }

    public static InputStream byteArrayToStream(byte[] dados) {
        return new ByteArrayInputStream(dados);
    }

    public static File saveToFileTemp(byte[] dados) throws Exception {
        return saveToFile(dados, getTempDir() + UUID.randomUUID().toString());
    }

    public static File saveToFileTemp(byte[] dados, String extencao) throws Exception {
        return saveToFileTemp(dados, extencao, "");
    }

    public static File saveToFileTemp(byte[] dados, String extencao, String subPath) throws Exception {
        extencao = extencao == null || extencao.isEmpty() ? "" : "." + extencao;
        subPath = subPath == null ? "" : subPath;
        if (!subPath.isEmpty() && !subPath.endsWith("/")) {
            subPath += "/";
        }

        String tempDir = getTempDir();
        if (!subPath.isEmpty()) {
            tempDir += subPath;
            File path = new File(tempDir);
            if (!path.exists()) {
                path.mkdirs();
            }
        }

        return saveToFile(dados, tempDir + UUID.randomUUID().toString() + extencao);
    }

    public static File saveToFile(byte[] dados, String fileName) throws Exception {
        File file = new File(fileName);
        try (OutputStream outStream = new FileOutputStream(file)) {
            outStream.write(dados);
            return file;
        }
    }

    public static File saveToFile(byte[] dados, File file) throws Exception {
        try (OutputStream outStream = new FileOutputStream(file)) {
            outStream.write(dados);
            return file;
        }
    }

    public static byte[] toBytes(File file) throws Exception {
        return Files.readAllBytes(file.toPath());
    }

    public static File getHttpFile(String url, String extencao) throws Exception {
        if (extencao == null) {
            extencao = "";
        }
        if (!extencao.isEmpty() && !extencao.startsWith(".")) {
            extencao = "." + extencao;
        }

        File file = new File(getTempDir() + UUID.randomUUID().toString() + extencao);

        InputStream in = new URL(url).openStream();

        try (ReadableByteChannel rbc = Channels.newChannel(in)) {
            FileOutputStream out = new FileOutputStream(file);
            try (FileChannel channel = out.getChannel()) {
                channel.transferFrom(rbc, 0, Long.MAX_VALUE);
            }
            return file;
        }
    }

    public static String getContent(File file) throws Exception {
        try (FileInputStream fis = new FileInputStream(file.getAbsolutePath())) {
            return IOUtils.toString(fis, "UTF-8");
        }
    }

    public static Document parseXmlDocument(String xml) throws Exception {
        DocumentBuilderFactory xmlBuild = DocumentBuilderFactory.newInstance();
        xmlBuild.setNamespaceAware(false);
        DocumentBuilder xmlBuilder = xmlBuild.newDocumentBuilder();
        ByteArrayInputStream input = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        Document docXml = xmlBuilder.parse(input);
        return docXml;

    }

    public static String extractExtencao(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }

        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).trim().toLowerCase();
    }

    public static String extractFileName(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return fileName;
        }
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public static BufferedWriter bufferedWriter(File file) throws IOException {
        return bufferedWriter(file, false);
    }

    public static BufferedWriter bufferedWriter(File file, boolean append) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), Charset.forName("UTF-8")));
    }

    public static BufferedWriter bufferedWriter(String file) throws IOException {
        return bufferedWriter(file, false);
    }

    public static BufferedWriter bufferedWriter(String file, boolean append) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), Charset.forName("UTF-8")));
    }

    public static BufferedReader bufferedReader(File file) throws IOException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
    }

    public static BufferedReader bufferedReader(String file) throws IOException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
    }

}
