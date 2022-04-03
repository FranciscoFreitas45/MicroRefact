package com.ukefu.util.ai;
 import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.app.phrase.Occurrence;
import org.ansj.app.phrase.PhraseExtractor;
import org.ansj.app.summary.SummaryComputer;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.library.AmbiguityLibrary;
import org.ansj.library.DicLibrary;
import org.ansj.library.StopLibrary;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.util.MyStaticValue;
import org.apache.commons.lang.StringUtils;
import com.ukefu.webim.web.model.Words;
public class DicSegment {

 private  List<String> librarykeyList;

 private  String loadpath;


public void addWord(List<String> words,String librarykey){
    if (DicLibrary.get(DicLibrary.DEFAULT + librarykey) == null) {
        MyStaticValue.ENV.put(DicLibrary.DEFAULT + librarykey, new File(loadpath, "ukefu-" + librarykey + ".dic").getAbsolutePath());
        librarykeyList.add(DicLibrary.DEFAULT + librarykey);
    }
    if (words != null && words.size() > 0) {
        for (String word : words) {
            DicLibrary.insert(DicLibrary.DEFAULT + librarykey, word);
        }
    }
}


@SuppressWarnings({ "unchecked", "rawtypes" })
public String summary(String content,int num){
    String summary = null;
    if (!StringUtils.isBlank(content) && num >= 0) {
        SummaryComputer sc = new SummaryComputer("", content);
        KeyWordComputer kwc = new KeyWordComputer(num);
        NlpAnalysis nlpAnalysis = new NlpAnalysis();
        nlpAnalysis.setForests(DicLibrary.gets(librarykeyList));
        kwc.setAnalysisType(nlpAnalysis);
        List<Keyword> result = kwc.computeArticleTfidf("", content);
        summary = sc.toSummary(result).getSummary();
    }
    return summary;
}


public void removeWord(String word,String librarykey){
    if (!StringUtils.isBlank(word)) {
        DicLibrary.delete(DicLibrary.DEFAULT + librarykey, word);
    }
}


public String[] byNature(String content,Set<String> expectedNature){
    List<String> wordList = new ArrayList<String>();
    if (!StringUtils.isBlank(content) && expectedNature != null && expectedNature.size() > 0) {
        // 分词结果的一个封装，主要是一个List<Term>的terms
        Result result = NlpAnalysis.parse(content, DicLibrary.gets(librarykeyList));
        // 拿到terms
        List<Term> terms = result.getTerms();
        for (int i = 0; i < terms.size(); i++) {
            // 拿到词
            String word = terms.get(i).getName();
            // 拿到词性
            String natureStr = terms.get(i).getNatureStr();
            if (expectedNature.contains(natureStr)) {
                wordList.add(word + "/" + natureStr);
            }
        }
    }
    return wordList.toArray(new String[wordList.size()]);
}


public boolean isChineseByBlock(char c){
    Character.UnicodeScript sc = Character.UnicodeScript.of(c);
    if (sc == Character.UnicodeScript.COMMON) {
        return true;
    } else {
        return false;
    }
}


public String[] segment(String content){
    List<Term> terms = null;
    if (librarykeyList != null && librarykeyList.size() > 0) {
        terms = NlpAnalysis.parse(content, DicLibrary.gets(librarykeyList)).getTerms();
    } else {
        terms = NlpAnalysis.parse(content).getTerms();
    }
    List<String> words = new ArrayList<String>();
    for (Term term : terms) {
        words.add(term.getName());
    }
    return words.toArray(new String[words.size()]);
}


public void loadDic(String path,List<Words> wordsList){
    loadpath = path;
    File dicPath = new File(path);
    if (!dicPath.exists()) {
        dicPath.mkdirs();
    }
    // ukefu词典
    File ukefudicFile = new File(path, "ukefu.dic");
    if (!ukefudicFile.getParentFile().exists()) {
        ukefudicFile.getParentFile().mkdirs();
    }
    if (!ukefudicFile.exists()) {
        ukefudicFile.createNewFile();
    }
    // 歧义词典
    File ambiguitydicFile = new File(path, "ambiguity.dic");
    if (!ambiguitydicFile.getParentFile().exists()) {
        ambiguitydicFile.getParentFile().mkdirs();
    }
    if (!ambiguitydicFile.exists()) {
        ambiguitydicFile.createNewFile();
    }
    // 停词词典
    File stopdicFile = new File(path, "stop.dic");
    if (!stopdicFile.getParentFile().exists()) {
        stopdicFile.getParentFile().mkdirs();
    }
    if (!stopdicFile.exists()) {
        stopdicFile.createNewFile();
    }
    try {
        librarykeyList = new ArrayList<String>();
        File[] tempList = dicPath.listFiles();
        for (File file : tempList) {
            String name = file.getName();
            if (file.isFile() && name.contains("ukefu")) {
                // 格式：[dic]+[类型id]
                String dicname = DicLibrary.DEFAULT + name.replaceAll(".dic", "").replaceAll("ukefu-", "");
                if (MyStaticValue.ENV.get(dicname) != null && !StringUtils.isBlank(MyStaticValue.ENV.get(dicname))) {
                    MyStaticValue.reloadLibrary(dicname);
                } else {
                    MyStaticValue.ENV.put(dicname, new File(path, name).getAbsolutePath());
                }
                librarykeyList.add(dicname);
            }
        }
        MyStaticValue.ENV.put(DicLibrary.DEFAULT, ukefudicFile.getAbsolutePath());
        // 歧义词典
        MyStaticValue.ENV.put(AmbiguityLibrary.DEFAULT, ambiguitydicFile.getAbsolutePath());
        // 停词词典
        MyStaticValue.ENV.put(StopLibrary.DEFAULT, stopdicFile.getAbsolutePath());
    } catch (Exception e) {
        e.printStackTrace();
    }
    // 加载字典文件
    if (wordsList != null && wordsList.size() > 0) {
        // 加载数据库词典表
        for (Words words : wordsList) {
            if (!StringUtils.isBlank(words.getContent())) {
                for (String word : words.getContent().split("[, ，:；;\\n\t ]")) {
                    if (!StringUtils.isBlank(word)) {
                        DicLibrary.insert(DicLibrary.DEFAULT + "ukefu", word);
                    }
                }
            }
        }
    }
}


public String[] keyphrase(String content,int num){
    List<String> keyphrase = new ArrayList<String>();
    if (!StringUtils.isBlank(content) && num >= 0) {
        PhraseExtractor pe = new PhraseExtractor();
        pe.fromText(content);
        pe.setAnalysis(new NlpAnalysis());
        pe.setLength(10);
        NlpAnalysis nlpAnalysis = new NlpAnalysis();
        nlpAnalysis.setForests(DicLibrary.gets(librarykeyList));
        pe.setAnalysis(nlpAnalysis);
        List<Map.Entry<String, Occurrence>> keyphraseList = pe.nbest(num);
        for (Map.Entry<String, Occurrence> map : keyphraseList) {
            keyphrase.add(map.getKey());
        }
    }
    return keyphrase.toArray(new String[keyphrase.size()]);
}


@SuppressWarnings({ "rawtypes", "unchecked" })
public String[] keyword(String content,int num){
    KeyWordComputer kwc = new KeyWordComputer(num);
    NlpAnalysis nlpAnalysis = new NlpAnalysis();
    nlpAnalysis.setForests(DicLibrary.gets(librarykeyList));
    kwc.setAnalysisType(nlpAnalysis);
    Collection<Keyword> result = kwc.computeArticleTfidf("", content);
    List<String> words = new ArrayList<String>();
    for (Keyword keyword : result) {
        words.add(keyword.getName());
    }
    return words.toArray(new String[words.size()]);
}


public void removeLibrary(String librarykey){
    if (!StringUtils.isBlank(librarykey)) {
        MyStaticValue.removeLibrary(DicLibrary.DEFAULT + librarykey);
    }
}


}