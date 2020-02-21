package com.irs.springmvcexporterwebapp.presentacion.util;

import java.util.regex.Pattern;

/**
 * Clase de utilidad para la validación de números NIF, NIE y CIF.
 *
 * @author IRS
 * @version 1.0.0
 */
public final class NifUtil {

    private NifUtil() {
    }

    // N.I.F - Número de Identificación Fiscal.
    public static boolean esNif(String nif) {
        if (nif == null) {
            return false;
        }

        nif = nif.toUpperCase();
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B',
            'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        String dni = nif.substring(0, nif.length() - 1);
        char letra = nif.charAt(nif.length() - 1);

        // Comprobamos la longitud de 9 digitos
        if (!Pattern.matches("^[A-Z,a-z,0-9]{9}$", nif)) {
            return false;
        }
        try {
            // Comprobamos si la letra calculada es igual a la del nif (Equivale a dni-23*(dni/23))
            if (letra != letras[Integer.parseInt(dni) % 23]) {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    // N.I.E - Número Identificador de Extranjeros.
    public static boolean esNie(String nie) {
        if (nie == null) {
            return false;
        }

        nie = nie.toUpperCase();  //LNNNNNNNL, L=Letra, N=Numero, Ejemplo: X0000980Z
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B',
            'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        char primerDigito = nie.charAt(0);

        // Comprobamos la longitud de 9 digitos
        if (!Pattern.matches("^[A-Z,a-z,0-9]{9}$", nie)) {
            return false;
        }
        // Comprobamos que el primer digito no sea un numero y sea una letra de KLMXYZ
        if (!Pattern.matches("[KLMXYZ]", String.valueOf(primerDigito))) {
            return false;
        }
        // Elimino la primera letra y lo valido como un nif
        nie = nie.substring(1, nie.length());
        String dni = nie.substring(0, nie.length() - 1);
        char letra = nie.charAt(nie.length() - 1);
        try {
            // Comprobamos si la letra calculada es igual a la del nie (Equivale a dni-23*(dni/23))
            int secuencia = Integer.parseInt(dni);
            switch (primerDigito) {
                case 'Y':
                    secuencia += 10000000;
                    break;
                case 'Z':
                    secuencia += 20000000;
                    break;
            }
            if (letra != letras[secuencia % 23]) {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    /**
     * El CIF (Código de Identificación Fiscal) es un elemento de identificación
     * administrativa para organizaciones y consta de 9 dígitos: X X X X X X X X
     * X ^ ^ ^ | | | | | | |__Numeros__| | | Dígito de Control, un numero o una
     * letra | (Aó1,Bó2,Có3,Dó4,Eó5,Fó6,Gó7,Hó8,Ió9,Jó0) | Letra de tipo de
     * organización, una de las siguientes: (A,B,C,D,E,F,G,H,K,L,M,N,P,Q,S)
     *
     * El primer dígito es una letra que indica el tipo de la organización y
     * puede ser una de los siguientes: A - Sociedad Anónima. B - Sociedad de
     * responsabilidad limitada. C - Sociedad colectiva. D - Sociedad
     * comanditaria. E - Comunidad de bienes y herencias yacentes. F - Sociedad
     * cooperativa. G - Asociación. H - Comunidades de propietarios en régimen
     * de propiedad horizontal. J - Sociedades civiles, con o sin personalidad
     * jurídica. K - Formato antiguo. L - Formato antiguo. M - Formato antiguo.
     * N - Personas jurídicas y entidades sin personalidad jurídica que carezcan
     * de la nacionalidad española. P - Corporación local. Q - Organismos
     * públicos. R - Congregaciones e instituciones religiosas. S - Órganos de
     * la Administración del Estado y de las Comunidades Autónomas. U - Uniones
     * Temporales de Empresas. V - Otros tipos no definidos en el resto de
     * claves. W - Establecimientos permanentes de entidad no residente en
     * España.
     *
     * Los siete dígitos siguientes son números y el último es el dígito de
     * control que puede ser un número ó una letra.
     *
     * En la version 1.0.3 Se añade la letra W a la comprobación de CIF's
     * extranjeros.
     *
     * @param cif Cig a comprobar.
     * @return true si es un cif correcto, false en caso contrario.
     */
    public static boolean esCif(String cif) {
        if (cif == null) {
            return false;
        }

        int[] VALORES = {0, 2, 4, 6, 8, 1, 3, 5, 7, 9};
        char[] DIGITO_CONTROL = {'J', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        int dc = 0;
        cif = cif.toUpperCase(); // CIF=B25398215 o B2539821E
        char dcCif = cif.charAt(cif.length() - 1);

        //Comprobamos la longitud de 9 digitos
        if (!Pattern.matches("^[A-Z,a-z,0-9]{9}$", cif)) {
            return false;
        }
        // Comprobamos la letra inicial que representa el Tipo de Organizacion
        if (!Pattern.matches("[ABCDEFGHJKLMNPQRSUVW]", String.valueOf(cif.charAt(0)))) {
            return false;
        }
        try {
            // Calculo el digito de control para su posterior comprobacion
            for (int i = 2; i <= 6; i += 2) {
                dc += VALORES[Integer.parseInt(cif.substring(i - 1, i))]; // Digitos Impares
                dc += Integer.parseInt(cif.substring(i, i + 1)); // Digitos Pares
            }
            dc += VALORES[Integer.parseInt(cif.substring(7, 8))]; // Ultimo Digito Impar
            dc = (10 - (dc % 10)) == 10 ? 0 : (10 - (dc % 10));
        } catch (NumberFormatException nfe) {
            return false;
        }

        // Si el digito de control del cif es una letra, transformo el digito
        // de control calculado a letra
        if (!Character.isDigit(dcCif)) {
            if (DIGITO_CONTROL[dc] != dcCif) {
                return false;
            }
        } else if (dc != Character.digit(dcCif, 10)) {
            return false;
        }

        return true;
    }
}
