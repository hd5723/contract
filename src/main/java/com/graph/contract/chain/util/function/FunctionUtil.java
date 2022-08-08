package com.graph.contract.chain.util.function;

import com.graph.contract.chain.util.abi.AbiInput;
import com.graph.contract.chain.util.abi.AbiModel;
import org.springframework.util.CollectionUtils;
import org.web3j.utils.Numeric;
import org.web3j.crypto.Hash;

public class FunctionUtil {

    public static String getFuntionSign(AbiModel abi) {
        StringBuilder function = new StringBuilder();
        function.append(abi.getName()).append("(");
        if(CollectionUtils.isEmpty(abi.getInputs())==false){
            for (int i = 0; i < abi.getInputs().size(); i++) {
                AbiInput input = abi.getInputs().get(i);
                function.append(input.getType());
                if(i< abi.getInputs().size()-1) {
                    function.append(",");
                }
            }
        }
        function.append(")");
        return function.toString();
    }

    public static String getFuntionSignSource(AbiModel abi) {
        StringBuilder function = new StringBuilder();
        function.append(abi.getName()).append("(");
        if(CollectionUtils.isEmpty(abi.getInputs())==false){
            for (int i = 0; i < abi.getInputs().size(); i++) {
                AbiInput input = abi.getInputs().get(i);

                //#TODO
                if(input.isIndexed()) {

                }

                function.append(input.getType());
                function.append(" ").append(input.getName());
                if(i< abi.getInputs().size()-1) {
                    function.append(",");
                }
            }
        }
        function.append(")");
        return function.toString();
    }

    public static String getSortHexString(String methodSignature) {
        //example		String methodSignature = "Upgraded(index_topic_1)";
        byte[] input = methodSignature.getBytes();
        byte[] hash = Hash.sha3(input);
        return Numeric.toHexString(hash).substring(0, 10);
    }

    public static String getHexString(String methodSignature) {
        //example	String methodSignature = "Upgraded(index_topic_1)";
        byte[] input = methodSignature.getBytes();
        byte[] hash = Hash.sha3(input);
        return Numeric.toHexString(hash);
    }

}
