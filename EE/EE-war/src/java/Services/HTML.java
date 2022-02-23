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

    public HTML multiSelect(String name, String placeholder, String option) {
        this.html = this.html
                + "<select x-cloak id='select' class='hidden'>"
                + option
                + "</select>"
                + ""
                + "<div x-data='dropdown()' x-init='loadOptions()' class='w-full flex flex-col h-52 mx-auto'>"
                + "  <label class='label'>"
                + "    <span class='label-text'>" + placeholder + "</span>"
                + "  </label>"
                + "  <input name='" + name + "' type='hidden' x-bind:value='selectedValues()'>"
                + "  <div class='inline-block relative w-full'>"
                + "    <div class='flex flex-col items-center relative'>"
                + "      <div x-on:click='open' class='w-full'>"
                + "        <div class='p-1 flex border border-gray-300 bg-white rounded-lg input input-bordered'>"
                + "          <div class='flex flex-auto flex-wrap'>"
                + "            <template x-for='(option,index) in selected' :key='options[option].value'>"
                + "              <div class='flex justify-center items-center m-1 font-medium py-1 px-1 bg-white rounded bg-gray-100 border'>"
                + "                <div class='text-xs font-normal leading-none max-w-full flex-initial x-model=' options[option] x-text='options[option].text'></div>"
                + "                <div class='flex flex-auto flex-row-reverse'>"
                + "                  <div x-on:click.stop='remove(index,option)'>"
                + "                    <svg class='fill-current h-4 w-4 ' role='button' viewBox='0 0 20 20'>"
                + "                      <path d='M14.348,14.849c-0.469,0.469-1.229,0.469-1.697,0L10,11.819l-2.651,3.029c-0.469,0.469-1.229,0.469-1.697,0"
                + "                                           c-0.469-0.469-0.469-1.229,0-1.697l2.758-3.15L5.651,6.849c-0.469-0.469-0.469-1.228,0-1.697s1.228-0.469,1.697,0L10,8.183"
                + "                                           l2.651-3.031c0.469-0.469,1.228-0.469,1.697,0s0.469,1.229,0,1.697l-2.758,3.152l2.758,3.15"
                + "                                           C14.817,13.62,14.817,14.38,14.348,14.849z' />"
                + "                    </svg>"
                + ""
                + "                  </div>"
                + "                </div>"
                + "              </div>"
                + "            </template>"
                + "            <div x-show='selected.length == 0' class='flex-1'>"
                + "              <input placeholder='" + placeholder + "' class='bg-transparent p-1 px-2 appearance-none outline-none h-full w-full text-gray-800' x-bind:value='selectedValues()'>"
                + "            </div>"
                + "          </div>"
                + "          <div class='text-gray-300 w-8 py-1 pl-2 pr-1 border-l flex items-center border-gray-200 svelte-1l8159u'>"
                + ""
                + "            <button type='button' x-show='isOpen() === true' x-on:click='open' class='cursor-pointer w-6 h-6 text-gray-600 outline-none focus:outline-none'>"
                + "              <svg version='1.1' class='fill-current h-4 w-4' viewBox='0 0 20 20'>"
                + "                <path d='M17.418,6.109c0.272-0.268,0.709-0.268,0.979,0s0.271,0.701,0,0.969l-7.908,7.83"
                + "	c-0.27,0.268-0.707,0.268-0.979,0l-7.908-7.83c-0.27-0.268-0.27-0.701,0-0.969c0.271-0.268,0.709-0.268,0.979,0L10,13.25"
                + "	L17.418,6.109z' />"
                + "              </svg>"
                + ""
                + "            </button>"
                + "            <button type='button' x-show='isOpen() === false' @click='close' class='cursor-pointer w-6 h-6 text-gray-600 outline-none focus:outline-none'>"
                + "              <svg class='fill-current h-4 w-4' viewBox='0 0 20 20'>"
                + "                <path d='M2.582,13.891c-0.272,0.268-0.709,0.268-0.979,0s-0.271-0.701,0-0.969l7.908-7.83"
                + "	c0.27-0.268,0.707-0.268,0.979,0l7.908,7.83c0.27,0.268,0.27,0.701,0,0.969c-0.271,0.268-0.709,0.268-0.978,0L10,6.75L2.582,13.891z"
                + "	' />"
                + "              </svg>"
                + ""
                + "            </button>"
                + "          </div>"
                + "        </div>"
                + "      </div>"
                + "      <div class='w-full px-4'>"
                + "        <div x-show.transition.origin.top='isOpen()' class='absolute shadow top-100 bg-white z-40 w-full left-0 rounded max-h-select' x-on:click.away='close'>"
                + "          <div class='flex flex-col w-full overflow-y-auto h-32'>"
                + "            <template x-for='(option,index) in options' :key='option' class='overflow-auto'>"
                + "              <div class='cursor-pointer w-full border-gray-100 rounded-t border-b hover:bg-gray-100' @click='select(index,$event)'>"
                + "                <div class='flex w-full items-center p-2 pl-2 border-transparent border-l-2 relative'>"
                + "                  <div class='w-full items-center flex justify-between'>"
                + "                    <div class='mx-2 leading-6' x-model='option' x-text='option.text'></div>"
                + "                    <div x-show='option.selected'>"
                + "                      <svg class='svg-icon' viewBox='0 0 20 20'>"
                + "                        <path fill='none' d='M7.197,16.963H7.195c-0.204,0-0.399-0.083-0.544-0.227l-6.039-6.082c-0.3-0.302-0.297-0.788,0.003-1.087"
                + "							C0.919,9.266,1.404,9.269,1.702,9.57l5.495,5.536L18.221,4.083c0.301-0.301,0.787-0.301,1.087,0c0.301,0.3,0.301,0.787,0,1.087"
                + "							L7.741,16.738C7.596,16.882,7.401,16.963,7.197,16.963z'></path>"
                + "                      </svg>"
                + "                    </div>"
                + "                  </div>"
                + "                </div>"
                + "              </div>"
                + "            </template>"
                + "          </div>"
                + "        </div>"
                + "      </div>"
                + "    </div>"
                + "  </div>"
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
