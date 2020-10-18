public class PhraseFilter implements Filter{

    private String request;
    private String phrase;

    public PhraseFilter(String req, String ph){
        request = req;
        phrase = ph;

    }

    public boolean satisfies(QuakeEntry qe){


            String info = qe.getInfo();
            if(request.equals("any")){
                String[] newArr = info.split("\\s+");
                for(String word : newArr)
                    if(word.contains(phrase))
                        return true;
            }
            if(request.equals("end")){
                int l = phrase.length();
                int i = info.length();
                String c = info.substring(i-l);
                if(c.equals(phrase))
                    return true;
            }

            if(request.equals("start")){
                int l = phrase.length();
                String c = info.substring(0,l);
                if(c.equals(phrase))
                    return true;
            }

            return false;

    }

    @Override
    public String getName() {
        return "PhraseFilter";
    }


}
