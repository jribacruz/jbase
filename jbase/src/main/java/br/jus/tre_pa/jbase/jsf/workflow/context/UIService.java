package br.jus.tre_pa.jbase.jsf.workflow.context;

import java.io.Serializable;

public interface UIService extends Serializable {

	void update(String id);

	void update(String formId, String id);

	void update(String formId, String parentId, String id);

	void execute(String wvar);

	void addCallbackParam(String name, String value);

	void scrollTo(String id);

	void scrollTo(String formId, String id);

	void scrollTo(String formId, String parentId, String id);

	void show(String wvar);

	void hide(String wvar);

	void hightlightEffect(String id);

	void hightlightEffect(String formId, String id);

	void hightlightEffect(String formId, String parentId, String id);

	void fireEvent(String eventKey, Object bean);
	
	void showError();
	
	void showGrowl();

}
