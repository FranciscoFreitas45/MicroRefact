package com.gbcom.common.drools.DroolsRuleEngineDBImpl;
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
public class DroolsRuleEngineHolder {

 private  DroolsRuleEngine INSTANCE;


}