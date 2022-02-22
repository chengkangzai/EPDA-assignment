/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author CCK
 */
public class HTML {

    private String html = "";

    public String getHtml() {
        return html;
    }

    public HTML wrap() {
        this.html = this.html
                + "<div class='w-1/3 mx-auto'> ";
        return this;
    }

    public HTML wrapped() {
        this.html = this.html + "</div>";
        return this;
    }

    public HTML select(String name, String placeholder, String options) {
        this.html = this.html
                + "<div class='form-control w-full'>"
                + "  <label class='label'>"
                + "    <span class='label-text'>" + placeholder + "</span>"
                + "  </label>"
                + "  <select class='select select-bordered' name='" + name + "'>"
                + "    <option disabled selected>Pick one</option>"
                + options
                + "  </select>"
                + "</div>";
        return this;
    }

    public HTML input(String type, String name, String placeholder, String value) {
        this.html = this.html
                + "<div class='form-control w-full'>"
                + "  <label class='label'>"
                + "    <span class='label-text'>" + name + "</span>"
                + "  </label>"
                + "  <input required "
                + " value='" + value + "' name='" + name.toLowerCase() + "' type='" + type + "' placeholder='" + placeholder + "' class='input input-bordered w-full'>"
                + "</div>";
        return this;
    }

    public HTML input(String type, String name, String value) {
        return this.input(type, name, name, value);
    }

    public HTML input(String type, String name) {
        return this.input(type, name, name, "");
    }

    public HTML submit(String value) {
        this.html = this.html
                + "<input type='submit' value='" + value + "' class='btn btn-primary btn-sm bg-purple-600 my-2' role='button'/>";
        return this;
    }

    public HTML submit() {
        return this.submit("Submit");
    }

    public HTML form(String method, String action) {
        this.html = this.html + "<form method='" + method + "' action='" + action + "' >";
        return this;
    }

    public HTML form() {
        this.html = this.html + "</form>";
        return this;
    }

    public HTML raw(String rawHtml) {
        this.html = this.html + rawHtml;
        return this;
    }

}
