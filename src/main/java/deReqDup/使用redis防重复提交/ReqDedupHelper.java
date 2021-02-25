package deReqDup.使用redis防重复提交;

import com.alibaba.fastjson.JSONObject;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class ReqDedupHelper {
    public String dedupParamMD5(final String reqJSON, String... excludeKeys) {
        TreeMap treeMap = JSONObject.parseObject(reqJSON, TreeMap.class);
        if (excludeKeys != null) {
            List<String> excludeList = Arrays.asList(excludeKeys);
            if (!excludeList.isEmpty()) {
                for (String excludeKey : excludeList) {
                    treeMap.remove(excludeKey);
                }
            }
        }

        String paramTreeMapJSON = JSONObject.toJSONString(treeMap);
        String md5 = jdkMD5(paramTreeMapJSON);

        return md5;

    }

    private String jdkMD5(String paramTreeMapJSON) {
        String res = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(paramTreeMapJSON.getBytes());
            res = DatatypeConverter.printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return res;
    }
}
