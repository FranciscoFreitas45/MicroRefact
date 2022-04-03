package com.gbcom.common.im;
 import com.gbcom.system.utils.TransformUtil;
public class IMUtil {

 private  String SN_PLACE_HOLDER;

 private  String SN_PLACE_HOLDER_REGEX;

 private  String CRC32_PLACE_HOLDER;

 private  String CRC32_PLACE_HOLDER_REGEX;


public String replaceSNCRC32(String atCmdHex,int seqNum){
    // 置入SN
    if (atCmdHex.contains(SN_PLACE_HOLDER)) {
        // 生成序列号
        String seqNumHex = Integer.toHexString(seqNum).toUpperCase();
        seqNumHex = "0000".substring(seqNumHex.length()) + seqNumHex;
        atCmdHex = atCmdHex.replaceAll(SN_PLACE_HOLDER_REGEX, seqNumHex);
    }
    // 置入CRC32
    if (atCmdHex.contains(CRC32_PLACE_HOLDER)) {
        // CRC处理
        // 默认值替换
        String tempAtCmdHex = atCmdHex.replaceAll(CRC32_PLACE_HOLDER_REGEX, "00000000");
        // 生成CRC32
        java.util.zip.CRC32 crc321 = new java.util.zip.CRC32();
        crc321.update(TransformUtil.hexStringToBytes(tempAtCmdHex));
        long crc32Value = crc321.getValue();
        byte[] bytes = TransformUtil.long2byteArray(crc32Value);
        String crc32Hex = Integer.toHexString(TransformUtil.byteArray2Int(new byte[] { bytes[4], bytes[5], bytes[6], bytes[7] }));
        crc32Hex = "00000000".substring(crc32Hex.length()) + crc32Hex.toUpperCase();
        // 置入CRC32校验码
        atCmdHex = atCmdHex.replaceAll(CRC32_PLACE_HOLDER_REGEX, crc32Hex);
    }
    return atCmdHex;
}


}