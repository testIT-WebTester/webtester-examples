package workshop;

import static info.novatec.testit.webtester.utils.Conditions.attributeWithValue;
import static info.novatec.testit.webtester.utils.Conditions.is;
import static info.novatec.testit.webtester.utils.Conditions.visible;
import static info.novatec.testit.webtester.utils.Identifications.tagName;
import static info.novatec.testit.webtester.utils.Waits.waitUntil;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Keys;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.enumerations.Caching;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;
import info.novatec.testit.webtester.junit.annotations.ConfigurationValue;
import info.novatec.testit.webtester.junit.annotations.CreateUsing;
import info.novatec.testit.webtester.junit.annotations.EntryPoint;
import info.novatec.testit.webtester.junit.runner.WebTesterJUnitRunner;
import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.Link;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.PasswordField;
import info.novatec.testit.webtester.pageobjects.TextArea;
import info.novatec.testit.webtester.pageobjects.TextField;
import info.novatec.testit.webtester.support.hamcrest.PageObjectMatcher;

@RunWith(WebTesterJUnitRunner.class)
public class IssueCreationTest {

	static final String URL = "https://testit-webtester.atlassian.net/projects/WORKSHOP/";

	@ConfigurationValue("username")
	static String username;
	@ConfigurationValue("password")
	static String password;

	@Resource
	@EntryPoint(URL)
	@CreateUsing(FirefoxFactory.class)
	static Browser browser;

	@Test
	public void createIssue() {

		IssueOverviewPage overview = browser.create(IssueOverviewPage.class);

		String summary = "Summary " + System.currentTimeMillis();

		Issue issue = new Issue();
		issue.setSummary(summary);
		issue.setDescription("The Description!");

		SearchResultPage searchResultPage = overview.login(username, password)
				.createIssue(issue)
				.searchForIssue(summary);

		boolean containsIssue = searchResultPage.issueListContainsIssueWithSummary(summary);
		assertThat(containsIssue, Matchers.is(true));

	}

	/* ------------------- */

	public static class IssueOverviewPage extends JiraPage<IssueOverviewPage> {

	}

	public static class JiraPage<T extends JiraPage<T>> extends PageObject {

		@IdentifyUsing("create_link")
		private Link createIssue;
		@IdentifyUsing("create-issue-dialog")
		private CreateIssueDialog createIssueDialog;

		@IdentifyUsing(value = "quickSearchInput", caching = Caching.OFF)
		private TextField quickSearch;

		public T login(String username, String password) {
			find(".login-link").click();
			create(LoginPage.class)
					.setUsername(username)
					.setPassword(password)
					.clickLogin();
			return (T) create(getClass());
		}

		public T createIssue(Issue issue) {
			clickCreateIssue().fillForm(issue).clickCreate();
			return (T) create(getClass());
		}

		public CreateIssueDialog clickCreateIssue() {
			waitUntil(createIssue, is(visible())).click();
			return waitUntil(createIssueDialog, is(visible()));
		}

		public SearchResultPage searchForIssue(String query){
			quickSearch.setText(query);
			quickSearch.getWebElement().sendKeys(Keys.ENTER);
			return create(SearchResultPage.class);
		}

	}

	private static class SearchResultPage extends JiraPage<SearchResultPage> {

		public boolean issueListContainsIssueWithSummary(String summary){
			return find(".issue-list")
					.findBy(tagName("li"))
					.asManyGenerics()
					.filter(attributeWithValue("title", summary))
					.size() == 1;
		}

	}

	public static class CreateIssueDialog extends PageObject {

		@IdentifyUsing("summary")
		private TextField summary;

		@IdentifyUsing("description")
		private TextArea description;

		@IdentifyUsing("create-issue-submit")
		private Button create;

		public CreateIssueDialog fillForm(Issue issue){
			return setSummary(issue.getSummary()).setDescription(issue.getDescription());
		}

		public CreateIssueDialog setSummary(String summary) {
			this.summary.setText(summary);
			return this;
		}

		public CreateIssueDialog setDescription(String description) {
			this.description.setText(description);
			return this;
		}

		public void clickCreate() {
			this.create.click();
		}

	}

	public static class LoginPage extends PageObject {

		@IdentifyUsing("username")
		private TextField username;

		@IdentifyUsing("password")
		private PasswordField password;

		@IdentifyUsing("login")
		private Button login;

		@PostConstruct
		public void verifyLoginPage(){
			MatcherAssert.assertThat(username, PageObjectMatcher.visible());
			MatcherAssert.assertThat(password, PageObjectMatcher.visible());
			MatcherAssert.assertThat(login, PageObjectMatcher.visible());
		}

		public LoginPage setUsername(String username) {
			this.username.setText(username);
			return this;
		}

		public LoginPage setPassword(String password) {
			this.password.setText(password);
			return this;
		}

		public void clickLogin() {
			this.login.click();
		}

	}

	public static class Issue {

		private String summary;
		private String description;

		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

}
