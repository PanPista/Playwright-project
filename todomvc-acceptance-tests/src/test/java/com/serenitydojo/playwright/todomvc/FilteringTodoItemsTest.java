package com.serenitydojo.playwright.todomvc;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.serenitydojo.playwright.fixtures.ChromeHeadlessOptions;
import com.serenitydojo.playwright.todomvc.pageobjects.TodoMvcAppPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.serenitydojo.playwright.data.TodoData.*;

@DisplayName("Filtering todo items")
@UsePlaywright(ChromeHeadlessOptions.class)
@Feature("Filtering todo items")
class FilteringTodoItemsTest {

    TodoMvcAppPage todoMvcApp;

    @BeforeEach
    void openApp(Page page) {
        todoMvcApp = new TodoMvcAppPage(page);
        todoMvcApp.open();

        todoMvcApp.addTodoItems(INITIAL_ITEMS);
    }

    @DisplayName("All items should be displayed by default")
    @Test
    void allItemsShouldBeDisplayedByDefault() {
        // TODO: Implement me
        // 1) Add "Feed the cat", "Walk the dog", "Buy some milk"
        // 2) Verify that the active filter is set to "All"
        assertThat(todoMvcApp.getAllFilterButton()).hasClass("selected");
        assertThat(todoMvcApp.getTodoItems()).hasText(INITIAL_ITEMS);
    }

    @DisplayName("Should be able to filter active items")
    @Test
    void shouldBeAbleToFilterByActiveItems() {
        // TODO: Implement me
        // 1) Add "Feed the cat", "Walk the dog", "Buy some milk"
        // 2) Complete "Walk the dog"
        // 3) Apply the "Active" filter
        // 4) Verify that only "Feed the cat" and "Buy some milk" are displayed
        todoMvcApp.completeTodoItem(WALK_THE_DOG);
        todoMvcApp.clickActiveFilterButton();
        assertThat(todoMvcApp.getTodoItems()).hasText(new String[] {FEED_THE_CAT, BUY_SOME_MILK});
    }

    @DisplayName("Should be able to filter completed items")
    @Test
    void shouldBeAbleToFilterByCompletedItems() {
        // TODO: Implement me
        // 1) Add "Feed the cat", "Walk the dog", "Buy some milk"
        // 2) Complete "Walk the dog"
        // 3) Apply the "Completed" filter
        // 4) Verify that only "Walk the dog" is displayed
        todoMvcApp.completeTodoItem(WALK_THE_DOG);
        todoMvcApp.clickCompletedFilterButton();
        assertThat(todoMvcApp.getTodoItems()).hasText(WALK_THE_DOG);
    }

    @DisplayName("Should be able to revert to showing all items")
    @Test
    void shouldBeAbleToRevertToShowingAllItems() {
        // TODO: Implement me
        // 1) Add "Feed the cat", "Walk the dog", "Buy some milk"
        // 2) Complete "Walk the dog"
        // 3) Apply the "Completed" filter
        // 4) Apply the "All" filter
        // 5) Verify that all three items ("Feed the cat", "Walk the dog", "Buy some milk") are displayed
        todoMvcApp.completeTodoItem(WALK_THE_DOG);
        todoMvcApp.clickCompletedFilterButton();
        todoMvcApp.clickAllFilterButton();
        assertThat(todoMvcApp.getTodoItems()).hasText(INITIAL_ITEMS);
    }
}
