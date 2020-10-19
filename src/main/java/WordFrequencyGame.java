import java.util.*;

public class WordFrequencyGame {

    private static final String WHITE_SPACE = "\\s+";

    public String getResult(String inputStr){
        if (inputStr.split(WHITE_SPACE).length==1) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split(WHITE_SPACE);
                List<Input> inputs = new ArrayList<>();
                for (String word : words) {
                    Input input = new Input(word, 1);
                    inputs.add(input);
                }
                //get the map for the next step of sizing the same word
                Map<String, List<Input>> wordMap = getListMap(inputs);
                List<Input> inputList = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : wordMap.entrySet()){
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    inputList.add(input);
                }
                inputs = inputList;
                sortWordFrequencyDescending(inputs);
                return formatResults(inputs);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private void sortWordFrequencyDescending(List<Input> inputs) {
        inputs.sort((word1, word2) -> word2.getFrequency() - word1.getFrequency());
    }

    private String formatResults(List<Input> inputs) {
        StringJoiner results = new StringJoiner("\n");
        for (Input input : inputs) {
            String result = input.getWord() + " " +input.getFrequency();
            results.add(result);
        }
        return results.toString();
    }


    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getWord())){
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getWord(), arr);
            }
            else {
                map.get(input.getWord()).add(input);
            }
        }
        return map;
    }


}
