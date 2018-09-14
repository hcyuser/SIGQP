import java.util.HashSet;
import java.util.Set;

public class User {
	double x,y;
	int id;
	Set<Integer> interest = new HashSet<>();

	public int hashCode() {
		int n = 0;
		for( Integer i: interest )
			n += i;
		return n;
	}
	public boolean equals( User u ) {
		return u.interest.equals( this.interest );
	}
}
