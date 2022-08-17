package additional;

public class SubStringSearch {
	static int i = 0;
public static boolean isSubString(String str, String subStr) {
	if(str == null || str.equals("")) {
		return false;
	}
	else if(subStr.equals(str.substring(i))) {
		return true;
	}
return 	 isSubString(str.substring(++i), subStr);

}
}
