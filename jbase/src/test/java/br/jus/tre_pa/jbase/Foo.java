package br.jus.tre_pa.jbase;

import org.hibernate.validator.constraints.NotEmpty;

public class Foo {
	@NotEmpty
	private String name;

	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
