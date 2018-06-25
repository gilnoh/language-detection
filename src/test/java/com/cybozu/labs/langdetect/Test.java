package com.cybozu.labs.langdetect;

import java.util.ArrayList;
import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.Language;

class Test {
    public void init(String profileDirectory) throws LangDetectException {
        DetectorFactory.loadProfile(profileDirectory);
    }

    public String detect(String text) throws LangDetectException {
        Detector detector = DetectorFactory.create();
        detector.append(text);
        return detector.detect();
    }

    public ArrayList<Language> detectLangs(String text) throws LangDetectException {
        System.out.println(text + "\t(" + text.length() + ")");
        Detector detector = DetectorFactory.create();
        detector.append(text);
        detector.noRandomForTextShoterThan(100);
        ArrayList<Language> langs = detector.getProbabilities();
        System.out.println("\t" + langs);
        return langs;
    }

    public static void main(String[] args) {
        Test a = new Test();

        try {
            a.init("/home/tailblues/omq/language-detection/src/main/resources/profiles");

            a.detectLangs("Hallo, wer kann mir helfen");
            a.detectLangs("lieber Kundenservice, habe gestern meine Brille");
            a.detectLangs("lieber kundenservice, habe gestern meine brille");
            a.detectLangs("hallo lieber kundenservice, habe gestern meine brille");
            a.detectLangs("neue kontaktlinse");
            a.detectLangs("neue Kontaktlinse");
            a.detectLangs("Am Donnrestag den 27.11.14");
            a.detectLangs("ICH HABE BEI IHNEN DIE BRILLE MANONO BESTELLT UND ERHALTEN.");
            a.detectLangs("Ich habe bei ihnen die Brille Manono bestellt und erhalten.");
            a.detectLangs("Hallo, ich interessiere mich sehr fuer die movie caviar");
            a.detectLangs("Sph -2   Add 2,00 Sph -1");
            a.detectLangs("Hallo, ich wollte mir gerade die Brille Cremona in bordeaux kaufen");
            a.detectLangs("ich wollte mir gerade die Brille Cremona in bordeaux kaufen");
            a.detectLangs("Hallo, Guten Tag.");
            a.detectLangs("Führe ich ein Export(Mixdown) durch, sind dort plötzlich 3 von 10 videos schwarz.");
            a.detectLangs("This is just a test question.");
            a.detectLangs("I like my music.");
            a.detectLangs("Wann kommt mein Paket");
            a.detectLangs("Wann kommt mein Paket?");

            a.detectLangs("I 1877 forlod Brandes København og bosatte sig i Berlin. Hans politiske synspunkter gjorde dog, at Preussen blev ubehagelig for ham at opholde sig i, og han vendte i 1883 tilbage til København, hvor han blev mødt af en helt ny gruppe af forfattere og tænkere, der var ivrige efter at modtage ham som deres leder. Det vigtigste af hans senere arbejder har været hans værk om William Shakespeare, der blev oversat til engelsk af William Archer og med det samme blev anerkendt.\n");
            a.detectLangs("VIDEO DE LUXE PREMIUM NON PERMETTEVA  PIU DI ESSERE APERTO INDICANDO UN CODICE DI ERRORE \n" +
                    "59  HO DOVUTO REINSTALLARLO MA CARICANDO I PROGETTI REALIZZATI IN PRECEDENZA ALL'ERRORE\n" +
                    "NON LI RICONOSCE PIU' SALTANDO ALCUNE TRANSIZIONI CHE AVEVO IMPOSTATO  AVENDO SCARICATO UN\n" +
                    "PACCHETTO (VIDEO DELUXE CONTENT PACK PREMIUM 2018)  CON CODICE  BUONO\n" +
                    "(G3-TDTRH-G2FCG-R2XTL-5Q239) CHE NON MI PERMETTE PIU DI SCARICARE PERCHE IL CODICE BUONO\n" +
                    "RISULTA GIA' UTILIZZATO.\n");
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }
}
