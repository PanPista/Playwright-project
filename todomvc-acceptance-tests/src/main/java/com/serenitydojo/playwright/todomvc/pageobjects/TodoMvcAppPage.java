package com.serenitydojo.playwright.todomvc.pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class TodoMvcAppPage {

    private final Page page;
    private final String baseUrl;
    private final Locator todoItems;
    private final Locator inputField;

    public TodoMvcAppPage(Page page) {
        this.page = page;
        baseUrl = (StringUtils.isEmpty(System.getenv("APP_HOST_URL"))) ? "http://localhost:7002" : System.getenv("APP_HOST_URL");

        todoItems = page.getByTestId("todo-item");
        inputField = page.getByTestId("text-input");
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

    public List<String> todoItemsDisplayed() {
        return todoItems.allTextContents();
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
        Locator item = todoItems.filter(new Locator.FilterOptions().setHasText(itemText)).first();
        Locator deleteButton = item.getByTestId("todo-item-button");
        item.hover();
        deleteButton.click();
    }

}
