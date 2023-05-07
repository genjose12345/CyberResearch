class Kasiski {
   public static void main(String[] args) {
      String code = "qwhtg nbcmn prdrg maasl editq cfeqi gjscf mzbwu " +
                    "sgqwe vvfcz vgjwd pzvgk bpaei agkyn eqbpq zanmv " + 
                    "xsovy tvdsc gjiaf rpqtk nvsog zbugd cbawf khtby " + 
                    "buchl ywurp ocrnz rchpq pyhcz";
      
      String [] subStr = new String[10];
      int [][] pos = new int[10][5];
      String tempCode;
      
      tempCode = formatCode(code);
      indexOfCoincidence(tempCode);
      findSubStrings(tempCode, subStr, pos);
      print(tempCode, subStr, pos);
   }
   public static String formatCode(String str) {
      str = str.toLowerCase();
      str = str.replaceAll("\\p{Punct}|\\s","");
      return str;
   }
   public static void findSubStrings(String str, String [] subStr, int [][] pos) {
      String temp;
      int position, rowCount = 0, colCount = 0;
      boolean newSubStr;
      
      for(int len = 3; len < str.length() / 2; len++) {
         for(int i = 0; i < str.length() - len; i++){
            newSubStr = true;            
            temp = str.substring(i,i+len);
            position = str.indexOf(temp, i+1);
            if(position != -1) {
               for(int k = 0; k < subStr.length; k++)
                  if(temp.equals(subStr[k]))
                     newSubStr = false;
               if(newSubStr) {
                  subStr[rowCount] = temp;
                  pos[rowCount][colCount] = i;
                  while(position != -1) {
                     colCount++;
                     pos[rowCount][colCount] = position;            
                     position = str.indexOf(temp, position + 1);
                  }
                  rowCount++;
               }
            }
            colCount = 0;   
         }
      }
   }
   public static void indexOfCoincidence(String str) {
      int [] freqCount = new int[26];
      double ic = 0;

      for(int i = 0; i < str.length(); i++)
         if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
            freqCount[str.charAt(i) - 'a']++;
      
      for(int i = 0; i < 26; i++) 
         ic += freqCount[i] * (freqCount[i] - 1);
      ic /= str.length() * (str.length() - 1);
// ic around 0.038 (Vigenere), ic around 0.068 (Caesar)
      System.out.println("The index of coincidence is " + ic + ".");

   }
   public static void print(String str, String [] subStr, int [][] pos) {
      for(int i = 0; i < subStr.length; i++)         
         if(subStr[i] == null)
            break;
         else
            for(int j = 0; j < pos[i].length; j++)
               if(j != 0 && pos[i][j] == 0)
                  break;
               else
                 System.out.println(subStr[i] + " -- " + pos[i][j]);

   }
}