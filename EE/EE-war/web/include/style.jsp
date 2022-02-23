<link href="https://cdn.jsdelivr.net/npm/daisyui@2.1.0/dist/full.css" rel="stylesheet" type="text/css" />
<style>
    .svg-icon {
        width: 1em;
        height: 1em;
    }

    .svg-icon path,
    .svg-icon polygon,
    .svg-icon rect {
        fill: #333;
    }

    .svg-icon circle {
        stroke: #4691f6;
        stroke-width: 1;
    }

</style>
<script src="https://cdn.tailwindcss.com"></script>
<script src="//unpkg.com/alpinejs" defer></script>
<script defer>
    function dropdown() {
        return {
            options: [],
            selected: [],
            show: false,
            open() {
                this.show = true;
            },
            close() {
                this.show = false;
            },
            isOpen() {
                return this.show === true;
            },
            select(index, event) {

                if (!this.options[index].selected) {

                    this.options[index].selected = true;
                    this.options[index].element = event.target;
                    this.selected.push(index);

                } else {
                    this.selected.splice(this.selected.lastIndexOf(index), 1);
                    this.options[index].selected = false;
                }
            },
            remove(i, option) {
                this.options[option].selected = false;
                this.selected.splice(i, 1);
            },
            loadOptions() {
                const options = document.getElementById('select').options;
                for (let i = 0; i < options.length; i++) {
                    console.log(options[i].getAttribute('selected'));
                    this.options.push({
                        value: options[i].value,
                        text: options[i].innerText,
                        selected: options[i].getAttribute('selected') !== null ? options[i].getAttribute('selected') : false
                    });
                }
            },
            selectedValues() {
                return this.selected.map((option) => {
                    return this.options[option].value;
                });
            }
        };
    }
</script>