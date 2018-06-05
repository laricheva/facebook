package extensions

import com.codeborne.selenide.*
import constants.ERROR_TOOLTIP_CLASS
import constants.IMAGE_UPLOAD_BAR_CLASS
import org.junit.Assert.fail
import org.openqa.selenium.By

const val WAIT_TIME: Long = 20000

fun By.get(): SelenideElement {
    return Selenide.`$`(this)
}

fun By.getAll(): ElementsCollection {
    return Selenide.`$$`(this)
}

fun String.get(): SelenideElement {
    return Selenide.`$`(this)
}

fun String.getAll(): ElementsCollection {
    return Selenide.`$$`(this)
}

fun SelenideElement.checkLinkPath(path: String): Boolean {
    return this.getAttribute("href").toString() == path
}

fun SelenideElement.checkColor(color: String): Boolean {
    return this.getCssValue(constants.COLOR_PROPERTY).getHexColor() == color
}

fun String.getHexColor(): String {
    val numbers = this.replace("rgba(", "").replace(")", "").split(",")

    val r = Integer.toHexString(Integer.parseInt(numbers[0].trim()))
    val g = Integer.toHexString(Integer.parseInt(numbers[1].trim()))
    val b = Integer.toHexString(Integer.parseInt(numbers[2].trim()))

    return "#$r$g$b"
}

fun SelenideElement.isContainText(text: String): Boolean {
    return this.text().contains(text)
}

fun SelenideElement.checkImage(src: String): Boolean {
    return this.getAttribute("src").toString() == src
}

fun SelenideElement.checkText(text: String) : Boolean{
    return this.text() == text;
}

fun SelenideElement.checkFormControlName(name: String): Boolean {
    return this.getAttribute(constants.FORM_CONTROL_NAME_ALL_LOW).toString() == name
}

fun SelenideElement.checkIsEmpty(): Boolean {
    return this.`is`(com.codeborne.selenide.Condition.empty)
}

fun SelenideElement.isPlaceholderEmpty(): Boolean {
    return this.attr(constants.PLACEHOLDER).isEmpty()
}

fun SelenideElement.isPlaceholderContainsText(text: String): Boolean {
    return this.attr(constants.PLACEHOLDER).contains(text)
}

fun SelenideElement.waitUntilDisappears() {
    this.waitUntil(Condition.disappears, WAIT_TIME)
}

fun getByExactText(text: String): SelenideElement {
    return getByXpath("//*[text()='$text']")
}

fun getByXpath(xpath: String): SelenideElement {
    return By.xpath(xpath).get()
}

fun getAllByXpath(xpath: String): ElementsCollection {
    return By.xpath(xpath).getAll()
}

fun getAllByClass(className: String): ElementsCollection {
    return getAllByXpath("//*[@class=\"$className\"]")
}

fun getByLinkText(text: String): SelenideElement {
    return By.linkText(text).get()
}

fun getByText(text: String, path: String): SelenideElement {
    return getByXpath("//*[text()='$text']$path")
}

fun getById(id: String): SelenideElement {
    return getByXpath("//*[@id=\"$id\"]")
}

fun getById(id: String, path: String): SelenideElement {
    return getByXpath("//*[@id=\"$id\"]/$path")
}

fun getByClass(className: String): SelenideElement {
    return getByXpath("//*[@class=\"$className\"]")
}

fun getByClass(className: String, path: String): SelenideElement {
    return getByXpath("//*[@class=\"$className\"]$path")
}

fun getByAttributeAndValue(atr: String, value: String): SelenideElement {
    return Selectors.by(atr, value).get()
}

fun getLabelByText(text: String): SelenideElement {
    return getByXpath("//label[text()='$text']")
}

fun getButtonByText(text: String): SelenideElement {
    return getByXpath("//button[text()='$text']")
}

fun getLabelByText(text: String, path: String): SelenideElement {
    return getByXpath("//label[text()='$text']/$path")
}

fun getByTag(tagName: String): SelenideElement {
    return getByXpath("//$tagName")
}

fun <T : Any> T?.notNull(f: (it: T) -> Unit, message: String) {
    if (this != null) f(this) else fail(message)
}

fun <T : Any> T?.notNull(): Boolean {
    return this != null
}

fun <T : Any> T?.notNullResponse(message: String): Any {
    return this ?: fail(message)
}

fun SelenideElement.getInputErrorTooltip(): SelenideElement {
    return this.parent().parent().find(By.className(ERROR_TOOLTIP_CLASS))
}

fun getImageUploadBar(imageTitle: String): SelenideElement {
    return getByExactText(imageTitle).parent().find(By.className(IMAGE_UPLOAD_BAR_CLASS))
}

fun String.dateFormat(): String { // yyyy-mm-dd => d/m/yyyy
    val dateList = ArrayList(this.split("-"))
    dateList.add(dateList.removeAt(0))
    val formattedDate = dateList.map { it -> it.removePrefix("0") }
    return formattedDate.joinToString("/")
}