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

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }
}
