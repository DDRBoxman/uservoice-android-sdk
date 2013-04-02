package com.uservoice.uservoicesdk.model;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.uservoice.uservoicesdk.rest.Callback;
import com.uservoice.uservoicesdk.rest.RestTaskCallback;

public class ClientConfig extends BaseModel {
	private boolean ticketsEnabled;
	private boolean feedbackEnabled;
	private boolean whiteLabel;
	private int defaultForumId;
	private List<CustomField> customFields;
	
	public static void loadClientConfig(final Callback<ClientConfig> callback) {
		doGet(apiPath("/client.json"), new RestTaskCallback(callback) {
			@Override
			public void onComplete(JSONObject result) {
				callback.onModel(deserializeObject(result, "client", ClientConfig.class));
			}
		});
	}
	
	@Override
	public void load(JSONObject object) throws JSONException {
		super.load(object);
		
		ticketsEnabled = object.getBoolean("tickets_enabled");
		feedbackEnabled = object.getBoolean("feedback_enabled");
		whiteLabel = object.getBoolean("white_label");
		defaultForumId = object.getJSONObject("forum").getInt("id");
		customFields = deserializeList(object, "custom_fields", CustomField.class);
	}
	
	public boolean isTicketSystemEnabled() {
		return ticketsEnabled;
	}
	
	public boolean isFeedbackEnabled() {
		return feedbackEnabled;
	}
	
	public boolean isWhiteLabel() {
		return whiteLabel;
	}
	
	public int getDefaultForumId() {
		return defaultForumId;
	}
	
	public List<CustomField> getCustomFields() {
		return customFields;
	}
}