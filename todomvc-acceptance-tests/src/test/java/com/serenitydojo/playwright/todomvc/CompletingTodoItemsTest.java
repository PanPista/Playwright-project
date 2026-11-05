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

@DisplayName("Completing todo items to the list")
@UsePlaywright(ChromeHeadlessOptions.class)
@Feature("Completing todo items to the list")
class CompletingTodoItemsTest {

    TodoMvcAppPage todoMvcApp;

    @BeforeEach
    void openApp(Page page) {
        todoMvcApp = new TodoMvcAppPage(page);

        todoMvcApp.open();
        todoMvcApp.addTodoItems(INITIAL_ITEMS);
    }

    @DisplayName("Completed items should be marked as completed")
    @Test
    void completedItemsShouldBeMarkedAsCompleted() {
        // TODO: Implement me
        // 1) Add "Feed the cat", "Walk the dog", "Buy some milk"
        // 2) Complete "Feed the cat"
        // 3) Check that "Feed the cat" is shown as completed
        todoMvcApp.completeTodoItem(FEED_THE_CAT);
        assertThat(todoMvcApp.getTodoItem(FEED_THE_CAT)).hasClass("completed");

    }

    @DisplayName("Completing an item should update the number of items left count")
    @Test
    void shouldUpdateNumberOfItemsLeftCount() {
        // TODO: Implement me
        // 1) Add "Feed the cat", "Walk the dog", "Buy some milk"
        // 2) Complete "Feed the cat"
        // 3) Verify the todo count shows "2 items left!"
        todoMvcApp.completeTodoItem(FEED_THE_CAT);
        assertThat(todoMvcApp.getTodoCount()).hasText("2 items left!");
    }

    @DisplayName("Should be able to clear completed items")
    @Test
    void shouldBeAbleToClearCompletedItems() {
        // TODO: Implement me
        // 1) Add "Feed the cat", "Walk the dog", "Buy some milk"
        // 2) Complete "Walk the dog"
        // 3) Clear the completed items
        // 4) Verify that the remaining items are "Feed the cat" and "Buy some milk"
        todoMvcApp.completeTodoItem(WALK_THE_DOG);
        todoMvcApp.clearCompletedItems();
        assertThat(todoMvcApp.getTodoItems()).hasText(new String[] {FEED_THE_CAT, BUY_SOME_MILK});
    }
}
