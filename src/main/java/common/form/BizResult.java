package common.form;

public class BizResult {

	private boolean successful;
	private String resultHint;
	private Object resultValue;

	private BizResult() {
	}

	public static BizResult success() {
		BizResult bizResult = new BizResult();
		bizResult.setSuccessful(true);
		bizResult.setResultHint("操作成功！");
		return bizResult;
	}

	public static BizResult error() {
		BizResult bizResult = new BizResult();
		bizResult.setSuccessful(false);
		bizResult.setResultHint("操作失败！");
		return bizResult;
	}

	public static BizResult error(String resultHint) {
		BizResult bizResult = new BizResult();
		bizResult.setSuccessful(false);
		bizResult.setResultHint(resultHint);
		return bizResult;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public BizResult setSuccessful(boolean successful) {
		this.successful = successful;
		return this;
	}

	public String getResultHint() {
		return resultHint;
	}

	public BizResult setResultHint(String resultHint) {
		this.resultHint = resultHint;
		return this;
	}

	public Object getResultValue() {
		return resultValue;
	}

	public BizResult setResultValue(Object resultValue) {
		this.resultValue = resultValue;
		return this;
	}
}
