package com.avilapps.pdf_services.utils;

public class NumberToWords {

    private static final String[] units = {
            "", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete",
            "ocho", "nueve", "diez", "once", "doce", "trece", "catorce",
            "quince", "dieciseis", "diecisiete", "dieciocho", "diecinueve",
            "veinte"
    };

    private static final String[] tens = {
            "",        // 0
            "",        // 1
            "veinti",  // 2
            "treinta",  // 3
            "cuarenta",   // 4
            "cincuenta",   // 5
            "sesenta",   // 6
            "setenta", // 7
            "ochenta",  // 8
            "noventa"   // 9
    };

    private static final String[] hundreds = {
            "",        // 0
            "ciento",        // 1
            "doscientos",  // 2
            "trescientos",  // 3
            "cuatrocientos",   // 4
            "quinientos",   // 5
            "seiscientos",   // 6
            "setecientos", // 7
            "ochocientos",  // 8
            "novecientos"   // 9
    };

    public static String convert(final int n) {
        if (n < 0) {
            return "minus " + convert(-n);
        }

        if (n <= 20) {
            return units[n];
        }

        if (n < 100) {
            return tens[n / 10] + ((n % 10 != 0 && n / 10 != 2) ? " y " : "") + units[n % 10];
        }

        if (n == 100) {
            return "cien";
        }

        if (n < 1000) {
            return hundreds[n / 100] + " " + convert(n % 100);
        }

        if (n < 1000000) {
            int thousandUnits = n / 1000;

            String thousandPart = "";
            if (thousandUnits > 1) {
                thousandPart =  convert(thousandUnits);
                if (thousandPart.endsWith("uno")) {
                    thousandPart = thousandPart.substring(0, thousandPart.length() - 1);
                }
                thousandPart += " ";
            }
            thousandPart += "mil";
            return thousandPart + ((n % 1000 != 0) ? " " : "") + convert(n % 1000);
        }

        if (n < 1000000000) {
            return convert(n / 1000000) + " million" + ((n % 1000000 != 0) ? " " : "") + convert(n % 1000000);
        }

        return convert(n / 1000000000) + " billion"  + ((n % 1000000000 != 0) ? " " : "") + convert(n % 1000000000);
    }
}
