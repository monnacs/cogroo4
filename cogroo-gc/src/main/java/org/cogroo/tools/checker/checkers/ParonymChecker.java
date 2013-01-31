/**
 * Copyright (C) 2012 cogroo <cogroo@cogroo.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cogroo.tools.checker.checkers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.cogroo.analyzer.AnalyzerI;
import org.cogroo.entities.Mistake;
import org.cogroo.text.Document;
import org.cogroo.text.Sentence;
import org.cogroo.text.Token;
import org.cogroo.text.impl.DocumentImpl;
import org.cogroo.tools.checker.AbstractChecker;

public class ParonymChecker extends AbstractChecker {

  private static final String ID_PREFIX = "word combination:";


  static final String ID = ID_PREFIX + "WORD_COMB_TOKEN";
  static final String CATEGORY = "Enganos ortográficos";
  static final String GROUP = "Ortografia";
  static final String DESCRIPTION = "Procura por enganos em parônimos.";
  static final String MESSAGE = "Possível problema com parônimos";
  static final String SHORT = "Parônimo.";
  
  private Map<String, String> dictionary;
  
  private static final Logger LOGGER = Logger.getLogger(ParonymChecker.class);


  private AnalyzerI analyzer;

  public ParonymChecker(AnalyzerI analyzer) {
    this.analyzer = analyzer;
    dictionary = new HashMap<String, String>();
    dictionary.put("dúvida", "duvida");
    dictionary.put("duvida", "dúvida");
  }
  
  public String getIdPrefix() {
    return ID_PREFIX;
  }

  public int getPriority() {
    return 311;
  }

  public List<Mistake> check(Sentence sentence) {

    for(Token t: sentence.getTokens()){
      String wanted = t.getLexeme().toLowerCase();
      if(dictionary.containsKey(wanted)){
        String sentenceText = sentence.getText();
        String alternativeText = sentenceText.substring(0, t.getStart()) +
            dictionary.get(wanted) + sentenceText.substring(t.getEnd());
        
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("\n****** Sentença alternativa **********:\n" + alternativeText);
        }
        
        Document alternative = new DocumentImpl(alternativeText);
        this.analyzer.analyze(alternative);
        
        if(sentence.getTokensProb() < alternative.getSentences().get(0).getTokensProb()){
          if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("\n****** Possível correção **********:\n" + sentenceText + " -> " + alternativeText);
          }
        }
      }
    }
    
    return Collections.emptyList();
  }

}