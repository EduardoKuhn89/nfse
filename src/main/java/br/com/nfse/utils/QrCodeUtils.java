package br.com.nfse.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.EnumMap;
import java.util.Map;

public final class QrCodeUtils {

    /**
     * Tamanho padrão em pixels (largura = altura).
     */
    private static final int DEFAULT_SIZE = 200;

    /**
     * Margem padrão em módulos ao redor do QR Code ("quiet zone").
     */
    private static final int DEFAULT_MARGIN = 1;

    /**
     * Formato de imagem gerado.
     */
    private static final String FORMAT = "PNG";

    private QrCodeUtils() {
    }

    public static byte[] toBytes(String content) {
        return builder().content(content).toBytes();
    }

    public static String toBase64(String content) {
        return builder().content(content).toBase64();
    }

    public static String toDataUrl(String content) {
        return builder().content(content).toDataUrl();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String content;
        private int size = DEFAULT_SIZE;
        private int margin = DEFAULT_MARGIN;
        private ErrorCorrectionLevel errorCorrection = ErrorCorrectionLevel.M;

        /**
         * Cor do módulo escuro (foreground) em ARGB. Padrão: preto.
         */
        private int colorDark = MatrixToImageConfig.BLACK;

        /**
         * Cor do fundo (background) em ARGB. Padrão: branco.
         */
        private int colorLight = MatrixToImageConfig.WHITE;

        private Builder() {
        }

        public Builder content(String content) {
            if (content == null || content.isEmpty()) {
                throw new IllegalArgumentException("content não pode ser nulo ou vazio");
            }
            this.content = content.trim();
            return this;
        }

        public Builder size(int size) {
            if (size < 10) {
                throw new IllegalArgumentException("size deve ser >= 10");
            }
            this.size = size;
            return this;
        }

        public Builder margin(int margin) {
            if (margin < 0) {
                throw new IllegalArgumentException("margin deve ser >= 0");
            }
            this.margin = margin;
            return this;
        }

        public Builder errorCorrection(ErrorCorrectionLevel level) {
            this.errorCorrection = level;
            return this;
        }

        public Builder colorDark(int argb) {
            this.colorDark = argb;
            return this;
        }

        public Builder colorLight(int argb) {
            this.colorLight = argb;
            return this;
        }

        public byte[] toBytes() {
            validate();
            try {
                BitMatrix matrix = encode();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                MatrixToImageConfig config = new MatrixToImageConfig(colorDark, colorLight);
                MatrixToImageWriter.writeToStream(matrix, FORMAT, baos, config);
                return baos.toByteArray();
            } catch (WriterException | IOException e) {
                throw new QrCodeException("Falha ao gerar QR Code para: " + content, e);
            }
        }

        public String toBase64() {
            return Base64.getEncoder().encodeToString(toBytes());
        }

        public String toDataUrl() {
            return "data:image/png;base64," + toBase64();
        }

        private BitMatrix encode() throws WriterException {
            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrection);
            hints.put(EncodeHintType.MARGIN, margin);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            return new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);
        }

        private void validate() {
            if (content == null || content.isEmpty()) {
                throw new IllegalStateException("content é obrigatório — chame content() antes");
            }
        }
    }

    public static class QrCodeException extends RuntimeException {

        public QrCodeException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
