package main.com.bigbank.dto;

public class InvestigationDto {

	private Double state;
	private Double people;
	private Double underworld;

	public Double getState() {
		return state;
	}

	public void setState(Double state) {
		this.state = state;
	}

	public Double getPeople() {
		return people;
	}

	public void setPeople(Double people) {
		this.people = people;
	}

	public Double getUnderworld() {
		return underworld;
	}

	public void setUnderworld(Double underworld) {
		this.underworld = underworld;
	}

	@Override
	public String toString() {
		return "InvestigationDto{" +
				"state=" + state +
				", people=" + people +
				", underworld=" + underworld +
				'}';
	}
}
