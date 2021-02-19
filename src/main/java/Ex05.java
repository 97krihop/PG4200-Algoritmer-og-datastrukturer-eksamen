
//0-500 A-F

public class Ex05 {
    public static byte[] compress(String idsAndGrades) {
        BitWriter bitWriter = new BitWriter();
        System.out.println(idsAndGrades);
        String[] persons = idsAndGrades.split("(?<=\\D)(?=\\d)");
        for(String s : persons){
            bitWriter.write(Integer.parseInt(s.substring(0, s.length() - 1)), 9);
            charWrite(s.substring(s.length() - 1), bitWriter);
        }
        return bitWriter.extract();
    }

    private static void charWrite(String s, BitWriter bitWriter) {
        if(s.equals("A")){
            bitWriter.write(false);
            bitWriter.write(false);
            bitWriter.write(false);
        }
        if(s.equals("B")){
            bitWriter.write(false);
            bitWriter.write(false);
            bitWriter.write(true);
        }
        if(s.equals("C")){
            bitWriter.write(false);
            bitWriter.write(true);
            bitWriter.write(false);
        }
        if(s.equals("D")){
            bitWriter.write(false);
            bitWriter.write(true);
            bitWriter.write(true);
        }
        if(s.equals("E")){
            bitWriter.write(true);
            bitWriter.write(false);
            bitWriter.write(false);
        }
        if(s.equals("F")){
            bitWriter.write(true);
            bitWriter.write(false);
            bitWriter.write(true);
        }
    }

    public static String decompress(byte[] data) {
        BitReader reader = new BitReader(data);
        StringBuilder s = new StringBuilder();
        while(true)
            try{
                s.append(reader.readInt(9));
                s.append(parseChar(reader));
            }catch(Exception e){
                break;
            }

        return s.toString();
    }

    private static char parseChar(BitReader bitReader) {
        boolean a = bitReader.readBoolean();
        boolean b = bitReader.readBoolean();
        boolean c = bitReader.readBoolean();

        if(!a && !b && !c) return 'A';
        if(a && !b && !c) return 'B';
        if(!a && b && !c) return 'C';
        if(a && b && !c) return 'D';
        if(!a && !b) return 'E';
        if(a && !b) return 'F';
        else throw new IllegalArgumentException();
    }
}
