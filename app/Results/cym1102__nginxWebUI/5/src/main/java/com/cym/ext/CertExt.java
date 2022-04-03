package com.cym.ext;
 import java.util.List;
import com.cym.model.Cert;
import com.cym.model.CertCode;
public class CertExt {

 private Cert cert;

 private List<CertCode> certCodes;


public Cert getCert(){
    return cert;
}


public List<CertCode> getCertCodes(){
    return certCodes;
}


public void setCertCodes(List<CertCode> certCodes){
    this.certCodes = certCodes;
}


public void setCert(Cert cert){
    this.cert = cert;
}


}