package com.gbcom.common.drools;
 import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.drools.RuleBase;
import org.drools.StatefulSession;
import org.drools.compiler.PackageBuilder;
import org.drools.spi.Activation;
import com.gbcom.common.drools.domain.DrlRule;
import com.gbcom.common.drools.domain.DrlRuleContext;
public class DroolsRuleEngineDBImpl implements DroolsRuleEngine{

 private  Logger logger;

 private  DroolsRuleEngine INSTANCE;

 private  RuleBase ruleBase;

private DroolsRuleEngineDBImpl() {
    initEngine();
}
public void initEngine(){
    // 设置时间格式
    System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
    try {
        synchronized (this) {
            ruleBase = RuleBaseFacatory.getRuleBase();
            // 优先从DB加载规则，如果没有加载到或者加载错误，则从文件系统加载
            PackageBuilder backageBuilder = getDBPackageBuilder();
            if (backageBuilder == null) {
                logger.error("initEngine ------- ERROR,,the rule fro,db is null,");
            } else {
                backageBuilder = null == backageBuilder ? getFilePackageBuilder() : backageBuilder;
                ruleBase.addPackages(backageBuilder.getPackages());
            }
        }
    } catch (Exception e) {
        logger.error("initEngine failed", e);
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


public List<Reader> readRuleDB(){
    List<Reader> readers = new ArrayList<Reader>();
    // DrlRuleService service =(DrlRuleService)SpringUtils.getBean("drlRuleService");
    List<DrlRule> list = new ArrayList<DrlRule>();
    for (DrlRule drlRule : list) {
        Date date = new Date();
        int hour = date.getHours();
        if (drlRule.getTrigerTime() != -1 && hour != drlRule.getTrigerTime()) {
            // 指定时间点升级
            continue;
        }
        if (drlRule.getState() != 2) {
            // 状态为运行时才升级
            continue;
        }
        String ruleContext = buildRuleContext(drlRule);
        Reader br = new StringReader(ruleContext);
        readers.add(br);
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
    statefulSession.fireAllRules(new org.drools.spi.AgendaFilter() {

        public boolean accept(Activation activation) {
            return !activation.getRule().getName().contains("_test");
        }
    });
    statefulSession.dispose();
}


public PackageBuilder getFilePackageBuilder(){
    // 装载规则文件
    List<Reader> readers;
    try {
        readers = readRuleFile();
        // 装载PackageBuilder
        return buildPackageBuilder(readers);
    } catch (FileNotFoundException e) {
        logger.error("", e);
        return null;
    } catch (Exception e) {
        logger.error("", e);
        return null;
    }
}


public String getOper(int type){
    switch(type) {
        case 0:
            return "==";
        case 1:
            return ">=";
        case 2:
            return "<=";
        case 3:
            return "!=";
        case 4:
            return ":";
        default:
            return "==";
    }
}


public void refreshEnginRule(){
    ruleBase = RuleBaseFacatory.getRuleBase();
    synchronized (ruleBase) {
        // 删除所有的添加的Package
        org.drools.rule.Package[] packages = ruleBase.getPackages();
        for (org.drools.rule.Package pg : packages) {
            ruleBase.removePackage(pg.getName());
        }
        // 重新初始化规则引擎
        initEngine();
    }
}


public DroolsRuleEngine getInstance(){
    return DroolsRuleEngineHolder.INSTANCE;
}


public PackageBuilder getDBPackageBuilder(){
    // 装载规则
    List<Reader> readers = readRuleDB();
    // 装载PackageBuilder
    try {
        return buildPackageBuilder(readers);
    } catch (Exception e) {
        logger.error("read Rule error ,", e);
        return null;
    }
}


public String buildRuleContext(DrlRule rule){
    /*
		String src = "package com.gbcom.drools.demo" + "\n" + 
		"import com.gbcom.drools.demo.PointDomain;" + "\n" + 
		"rule birthdayPoint" + "\n" + 
		"// 过生日，则加10分，并且将当月交易比数翻倍后再计算积分" + "\n" + 
		"salience 100" + "\n" + 
		"lock-on-active true" + "\n" + 
		"when" + "\n" + 
		"$pointDomain : PointDomain(birthDay == true)" + "\n" + 
		"then" + "\n" + 
		"$pointDomain.setPoint($pointDomain.getPoint()+10);" + "\n" + 
		"$pointDomain.recordPointLog($pointDomain.getUserName(),\"birthdayPoint\");" + "\n" + 
		"end" + "\n";
		*/
    String condition = "(";
    for (DrlRuleContext each : rule.getRules()) {
        condition += each.getItem() + getOper(each.getType()) + "\"" + each.getContext().trim() + "\",";
    }
    condition = condition.substring(0, condition.lastIndexOf(","));
    condition += ");";
    StringBuffer base = new StringBuffer("package com.gbcom.common.drools" + "\n" + "import com.gbcom.ccsv3.domain.ApDevice;" + "\n" + "import com.gbcom.ccsv3.template.policy.AutoRebootHandler;" + "\n" + "import com.gbcom.ccsv3.common.bean.ApCache;" + "\n");
    base.append("rule \"" + rule.getName() + "\"\n");
    base.append("salience " + rule.getScope() + "\n");
    base.append("when\n");
    base.append("$domain : ApCache" + condition + "\n");
    base.append("then\n");
    base.append("$domain.drlUpdate(\"" + rule.getVersion() + "\");\n");
    if (rule.isNeedReboot()) {
        base.append("AutoRebootHandler.getInstance().add($domain.getDevMac());\n");
    }
    base.append("drools.retract($domain);\n");
    base.append("end \n");
    logger.info("--base--\n" + base);
    return base.toString();
}


public PackageBuilder buildPackageBuilder(List<Reader> readers){
    if (null == readers || 0 == readers.size()) {
        return null;
    }
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


public boolean accept(Activation activation){
    return !activation.getRule().getName().contains("_test");
}


}