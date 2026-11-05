package com.serenitydojo.playwright.todomvc.pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.apache.commons.lang3.StringUtils;

public class TodoMvcAppPage {

    private final Page page;
    private final String baseUrl;
    private final Locator todoItems;
    private final Locator inputField;
    private final Locator todoCount;
    private final Locator clearCompletedButton;
    private final Locator allFilterButton;
    private final Locator activeFilterButton;
    private final Locator completedFilterButton;

    public static final String TODO_INPUT_PLACEHOLDER = "What needs to be done?";

    public TodoMvcAppPage(Page page) {
        this.page = page;
        baseUrl = (StringUtils.isEmpty(System.getenv("APP_HOST_URL"))) ? "http://localhost:7002" : System.getenv("APP_HOST_URL");

        todoItems = page.getByTestId("todo-item");
        inputField = page.getByTestId("text-input");
        todoCount = page.getByTestId("footer").locator(".todo-count");
        clearCompletedButton = page.getByTestId("footer").locator(".clear-completed");

        allFilterButton = page.getByTestId("footer").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("All").setExact(true));
        activeFilterButton = page.getByTestId("footer").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Active").setExact(true));
        completedFilterButton = page.getByTestId("footer").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Completed").setExact(true));
    }

    public void open() {
        page.navigate(baseUrl);
    }

    // TODO: Add page object methods here
    public Locator getTodoItems() {
        return todoItems;
    }

    public Locator getInputField() {
        return inputField;
    }

    public void addTodoItem(String itemText) {
        inputField.click();
        inputField.fill(itemText);
        inputField.press("Enter");
    }

    public void addTodoItems(String... items) {
        for (String item : items) {
            addTodoItem(item);
        }
    }

    public void deleteTodoItem(String itemText) {
        Locator item = getTodoItem(itemText);
        Locator deleteButton = item.getByTestId("todo-item-button");
        item.hover();
        deleteButton.click();
    }

    public void completeTodoItem(String itemText) {
        Locator item = getTodoItem(itemText);
        Locator checkbox = item.getByTestId("todo-item-toggle");
        checkbox.check();
    }

    public Locator getTodoItem(String itemText) {
        return todoItems.filter(new Locator.FilterOptions().setHasText(itemText)).first();
    }

    public Locator getTodoCount() {
        return todoCount;
    }

    public void clearCompletedItems() {
        clearCompletedButton.click();
    }

    public Locator getAllFilterButton() {
        return allFilterButton;
    }

    public void clickActiveFilterButton() {
        activeFilterButton.click();
    }

    public void clickCompletedFilterButton() {
        completedFilterButton.click();
    }
    public void clickAllFilterButton() {
        allFilterButton.click();
    }
}
