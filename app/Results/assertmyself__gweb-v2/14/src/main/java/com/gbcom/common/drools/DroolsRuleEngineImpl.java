package com.gbcom.common.drools;
 import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.drools.RuleBase;
import org.drools.StatefulSession;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
public class DroolsRuleEngineImpl implements DroolsRuleEngine{

 private  Logger logger;

 private  DroolsRuleEngine INSTANCE;

 private  RuleBase ruleBase;

private DroolsRuleEngineImpl() {
    initEngine();
}
public void initEngine(){
    // 设置时间格式
    System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
    ruleBase = RuleBaseFacatory.getRuleBase();
    try {
        PackageBuilder backageBuilder = getFilePackageBuilder();
        ruleBase.addPackages(backageBuilder.getPackages());
    } catch (DroolsParserException e) {
        e.printStackTrace();
        logger.error("drools parse failed!!!", e);
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("drools other err failed!!!", e);
    }
}


public List<Reader> readRuleFile(){
    List<Reader> readers = new ArrayList<Reader>();
    String filePath = this.getClass().getClassLoader().getResource("drl/").getFile();
    File file = new File(filePath);
    File[] files = file.listFiles();
    if (files == null) {
        throw new FileNotFoundException("drl =" + filePath);
    }
    for (File f : files) {
        if (f.isDirectory()) {
        } else {
            readers.add(new FileReader(f));
        }
    }
    return readers;
}


public void executeRuleEngine(List<Object> domain){
    if (null == ruleBase.getPackages() || 0 == ruleBase.getPackages().length) {
        return;
    }
    StatefulSession statefulSession = ruleBase.newStatefulSession();
    for (Object each : domain) {
        statefulSession.insert(each);
    }
    // fire
    statefulSession.fireAllRules();
    statefulSession.dispose();
}


public PackageBuilder getFilePackageBuilder(){
    // 装载测试脚本文件
    List<Reader> readers = readRuleFile();
    PackageBuilder backageBuilder = new PackageBuilder();
    for (Reader r : readers) {
        backageBuilder.addPackageFromDrl(r);
    }
    // 检查脚本是否有问题
    if (backageBuilder.hasErrors()) {
        throw new Exception(backageBuilder.getErrors().toString());
    }
    return backageBuilder;
}


public void refreshEnginRule(){
    ruleBase = RuleBaseFacatory.getRuleBase();
    org.drools.rule.Package[] packages = ruleBase.getPackages();
    for (org.drools.rule.Package pg : packages) {
        ruleBase.removePackage(pg.getName());
    }
    initEngine();
}


public DroolsRuleEngine getInstance(){
    return DroolsRuleEngineHolder.INSTANCE;
}


}