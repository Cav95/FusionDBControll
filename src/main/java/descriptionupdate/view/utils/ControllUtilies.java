package descriptionupdate.view.utils;

import java.io.IOException;
import java.util.Arrays;

import descriptionupdate.model.api.Description;
import descriptionupdate.view.api.ProibenCaratter;
import descriptionupdate.view.exception.BlankDescriptionException;

import org.languagetool.JLanguageTool;
import org.languagetool.language.identifier.LanguageIdentifier;
import org.languagetool.rules.RuleMatch;


public class ControllUtilies {

    public static boolean isProhibitedCharacter(String character) {
        return Arrays.asList(ProibenCaratter.values()).stream()
                .anyMatch(c -> character.contains(c.getCharacter()));
    }

    public static void descriptionValidCaracter(Description description) {
        if (ControllUtilies.isProhibitedCharacter(description.itaDescripion())
                || ControllUtilies.isProhibitedCharacter(description.engDescription())) {
            throw new IllegalArgumentException();
        }
    }

    public static void descriptionNotBlank(Description description) {
        if (description.itaDescripion().isBlank() || description.engDescription().isBlank()) {
            throw new BlankDescriptionException("Description cannot be blank");
        }
    }
/*    
public static void correggiTestoAutomaticamente(String testo) {
        System.out.println("Testo originale: \"" + testo + "\"");

        // 1. IDENTIFICA LA LINGUA
        var linguaRilevata = LANG_IDENTIFIER.detectLanguage(testo);

        // 2. VERIFICA SE LA LINGUA È STATA RILEVATA CON SUFFICIENTE SICUREZZA
        // È una buona pratica controllare la probabilità per evitare di usare un correttore sbagliato
        if (linguaRilevata != null && linguaRilevata.getProbability() > 0.7) {
            Language lingua = linguaRilevata.getLanguage();
            System.out.println("Lingua rilevata: " + lingua.getName() + " con probabilità: " + String.format("%.2f", linguaRilevata.getProbability()));

            try {
                // 3. INIZIALIZZA IL CORRETTORE CON LA LINGUA RILEVATA
                JLanguageTool langTool = new JLanguageTool(lingua);

                // 4. ESEGUI LA CORREZIONE
                List<RuleMatch> matches = langTool.check(testo);

                if (matches.isEmpty()) {
                    System.out.println("Nessun errore trovato.");
                } else {
                    System.out.println("Errori trovati: " + matches.size());
                    for (RuleMatch match : matches) {
                        System.out.println("- Errore: " + match.getMessage());
                        System.out.println("  Testo errato: '" + testo.substring(match.getFromPos(), match.getToPos()) + "'");
                        System.out.println("  Suggerimenti: " + match.getSuggestedReplacements());
                    }
                }
            } catch (IOException e) {
                // Questa eccezione potrebbe verificarsi se LanguageTool non riesce a caricare le regole
                // per la lingua rilevata (es. dipendenza Maven mancante).
                System.out.println("Errore: Impossibile caricare il correttore per la lingua '" + lingua.getName() + "'. Controlla le dipendenze.");
                // e.printStackTrace();
            }

        } else {
            System.out.println("Impossibile determinare la lingua con sufficiente certezza. Correzione non eseguita.");
        }
    
    }*/
}
