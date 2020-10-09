public class quiz {
    public void findAbc(String input){
        int index = input.indexOf("abc");
        while (true){
            if (index == -1 || index >= input.length() - 3){
                break;
            }

//code

            System.out.println(index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            System.out.println("index after updating " + index);
            index = input.indexOf("abc",index+3);

        }
    }

    public void test(){
        //findAbc("abcd");
        findAbc("abcabcabcabca");
    }

    public static void main(String[] args){

        quiz Tr = new quiz();
        Tr.test();
    }
}
