import java.util.HashMap;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

class BoardGame {
    WeightedQuickUnionUF uf;
    HashMap<Integer, Character> type;
    HashMap<Integer, Integer> bounded;
    // Create a direction array for four direction searching
    int [][] m = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int height, width;

    /**
     * Initialize the board with corresponding size
     * @param h The height of the board
     * @param w The width of the board
     */
    public BoardGame(int h, int w){
        // Using the Quick Union data structure to store the connected and union status
        // Using the Hash table to store the stone type in each coordinates
        uf = new WeightedQuickUnionUF(h * w);
        type = new HashMap<Integer, Character>();
        bounded = new HashMap<Integer, Integer>();
        height = h; width = w;
    }

    /**
     * The function to put the stone in corresponding position
     * @param x x direction coordinate
     * @param y y direction coordinate
     * @param stoneType The type of the stone like 'O' or 'X'
     */
    public void putStone(int[] x, int[] y, char stoneType){
        for(int i = 0; i < x.length; i++){
            type.put(x[i] * width + y[i], stoneType); // store the type of each position
            if(x[i] == 0 || x[i] == height -1 || y[i] == 0 || y[i] == width -1)
                bounded.put(x[i] * width + y[i], width * height);
            else
                bounded.put(x[i] * width + y[i], 4);

            // find up, down, right and left. If there is any same type and not in the same component, then union
            for(int j = 0; j < 4; j++){
                int k = x[i] + m[j][0];
                int l = y[i] + m[j][1];
                if (k < 0 || k > height - 1 || l < 0 || l > width - 1)
                    continue;

                if(getStoneType(k, l) == getStoneType(x[i], y[i]) &&
                        uf.find(x[i] * width + y[i]) != uf.find(k * width + l)) {
                    int root1 = uf.find(x[i] * width + y[i]);
                    int root2 = uf.find(k * width + l);
                    uf.union(x[i] * width + y[i], k * width + l);
                    bounded.replace(uf.find(x[i] * width + y[i]),
                            bounded.get(root1) + bounded.get(root2) - 2);
                }
                else if (getStoneType(k, l) != getStoneType(x[i], y[i]) && getStoneType(k, l) != 'N'){
                    bounded.replace(uf.find(k * width + l), bounded.get(uf.find(k * width + l)) - 1);
                    bounded.replace(uf.find(x[i] * width + y[i]), bounded.get(uf.find(x[i] * width + y[i])) - 1);
                }
            }
        }
    }

    /**
     * Check the position is surrounded or not
     * @param x x coordinate
     * @param y y coordinate
     * @return The status shown surrounded or not
     */
    public boolean surrounded(int x, int y) {
        if(x == 0 || x == height - 1 || y == 0 || y == width - 1)
            return false;
        else{
            return bounded.get(uf.find(x * width + y)) == 0;
        }
    }

    /**
     * Get the type of stone is corresponding coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @return The type of stone in corresponding position 'O' or 'X'
     */
    public char getStoneType(int x, int y){
        // Searching the stone type of corresponding index through the Hash Table
        return type.getOrDefault(x * width + y, 'N');
    }

    /**
     * Get the number of connected regions
     * @return The numbers of the connected region
     */
    public int countConnectedRegions(){
        return uf.count() + type.size() - height * width;
    }

//    // Below are the test region
//    public static void test(String[] args){
//        BoardGame g;
//        JSONParser jsonParser = new JSONParser();
//        try (FileReader reader = new FileReader(args[0])){
//            JSONArray all = (JSONArray) jsonParser.parse(reader);
//            int count = 0;
//            for(Object CaseInList : all){
//                count++;
//                JSONArray a = (JSONArray) CaseInList;
//                int testSize = 0; int waSize = 0;
//                System.out.print("Case ");
//                System.out.println(count);
//                //Board Setup
//                JSONObject argsSeting = (JSONObject) a.get(0);
//                a.remove(0);
//
//                JSONArray argSettingArr = (JSONArray) argsSeting.get("args");
//                g = new BoardGame(
//                        Integer.parseInt(argSettingArr.get(0).toString())
//                        ,Integer.parseInt(argSettingArr.get(1).toString()));
//
//                for (Object o : a)
//                {
//                    JSONObject person = (JSONObject) o;
//
//                    String func =  person.get("func").toString();
//                    JSONArray arg = (JSONArray) person.get("args");
//
//                    switch(func){
//                        case "putStone":
//                            int xArray[] = JSONArraytoIntArray((JSONArray) arg.get(0));
//                            int yArray[] = JSONArraytoIntArray((JSONArray) arg.get(1));
//                            String stonetype =  (String) arg.get(2);
//
//                            g.putStone(xArray,yArray,stonetype.charAt(0));
//                            break;
//                        case "surrounded":
//                            Boolean answer = (Boolean) person.get("answer");
//                            testSize++;
//                            System.out.print(testSize + ": " + func + " / ");
//                            Boolean ans = g.surrounded(
//                                    Integer.parseInt(arg.get(0).toString()),
//                                    Integer.parseInt(arg.get(1).toString())
//                            );
//                            if(ans==answer){
//                                System.out.println("AC");
//                            }else{
//                                waSize++;
//                                System.out.println("WA");
//                            }
//                            break;
//                        case "countConnectedRegions":
//                            testSize++;
//                            int ans2 = Integer.parseInt(arg.get(0).toString());
//                            int ansCR = g.countConnectedRegions();
//                            System.out.print(testSize + ": " + func + " / ");
//                            if(ans2==ansCR){
//                                System.out.println("AC");
//                            }else{
//                                waSize++;
//                                System.out.println("WA");
//                            }
//                    }
//
//                }
//                System.out.println("Score: " + (testSize-waSize) + " / " + testSize + " ");
//            }
//        }catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static int[] JSONArraytoIntArray(JSONArray x){
//        int sizeLim = x.size();
//        int MyInt[] = new int[sizeLim];
//        for(int i=0;i<sizeLim;i++){
//            MyInt[i]= Integer.parseInt(x.get(i).toString());
//        }
//        return MyInt;
//    }
//
//    public static void main(String[] args) {
//        test(args);
//    }
}
