package plannerLib;

public class Predicate {

	protected String type, id, value;

	public Predicate(String type, String id, String value) {
		this.type = type;
		this.id = id;
		this.value = value;
	}

	public boolean satisfies(Predicate predicate) {

		return (this.type.equals(predicate.type) && (this.id.equals(predicate.id) || predicate.id.equals("?"))
				&& this.value.equals(predicate.value));
	}

	public boolean contradicts(Predicate predicate) {

		return this.type.equals(predicate.type)
				&& (this.id.equals(predicate.id) && (!this.value.equals(predicate.value)));
	}

	@Override
	public int hashCode() {
		return (type + id + value).hashCode();
	}

	@Override
	public String toString() {

		return type + "_" + id + "=" + value;
	}

	public boolean equals(Predicate p) {
		
		return (this.type.equals(p.type) && (this.id.equals(p.id) && this.value.equals(p.value)));
	}
	
	
	

}
