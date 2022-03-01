<link href="https://cdn.jsdelivr.net/npm/daisyui@2.1.0/dist/full.css" rel="stylesheet" type="text/css" />
<style>
    [x-cloak] {
        display: none;
    }
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
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.4/datatables.min.css"/>

<script src="https://cdn.tailwindcss.com"></script>
<script defer src="//unpkg.com/alpinejs" defer></script>
<script>
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
<script>
    const MONTH_NAMES = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", ];
    const MONTH_SHORT_NAMES = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", ];
    const DAYS = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

    function app() {
        return {
            showDatepicker: false,
            datepickerValue: "",
            selectedDate: "",
            dateFormat: "YYYY-MM-DD",
            month: "",
            year: "",
            no_of_days: [],
            blankdays: [],
            initDate(newDate) {
                let today;
                if (newDate) {
                    this.selectedDate = newDate;
                    console.log(newDate)
                }
                if (this.selectedDate) {
                    today = new Date(this.selectedDate.replace(/-/g, "/"));
                } else {
                    today = new Date();
                }
                this.month = today.getMonth();
                this.year = today.getFullYear();
                this.datepickerValue = this.formatDateForDisplay(today);
            },
            formatDateForDisplay(date) {
                let formattedDay = DAYS[date.getDay()];
                let formattedDate = ("0" + date.getDate()).slice(-2); // appends 0 (zero) in single digit date
                let formattedMonth = MONTH_NAMES[date.getMonth()];
                let formattedMonthShortName = MONTH_SHORT_NAMES[date.getMonth()];
                let formattedMonthInNumber = ("0" + (parseInt(date.getMonth()) + 1)).slice(-2);
                let formattedYear = date.getFullYear();
                if (this.dateFormat === "DD-MM-YYYY") {
                    return formattedDate + "-" + formattedMonthInNumber + "-" + formattedYear;// 02-04-2021
                }
                if (this.dateFormat === "YYYY-MM-DD") {
                    return formattedYear + "-" + formattedMonthInNumber + "-" + formattedDate;
                }
                if (this.dateFormat === "D d M, Y") {
                    return formattedDay + " " + formattedDate + " " + formattedMonthShortName + " " + formattedYear;
                }
                return formattedDay + " " + formattedDate + " " + formattedMonth + " " + formattedYear;
            },
            isSelectedDate(date) {
                const d = new Date(this.year, this.month, date);
                return this.datepickerValue === this.formatDateForDisplay(d);
            },
            isToday(date) {
                const today = new Date();
                const d = new Date(this.year, this.month, date);
                return today.toDateString() === d.toDateString();
            },
            getDateValue(date) {
                let selectedDate = new Date(this.year, this.month, date);
                this.datepickerValue = this.formatDateForDisplay(selectedDate);
                this.isSelectedDate(date);
                this.showDatepicker = false;
            },
            getNoOfDays() {
                let daysInMonth = new Date(this.year, this.month + 1, 0).getDate();
                // find where to start calendar day of week
                let dayOfWeek = new Date(this.year, this.month).getDay();
                let blankdaysArray = [];
                for (let i = 1; i <= dayOfWeek; i++) {
                    blankdaysArray.push(i);
                }
                let daysArray = [];
                for (let j = 1; j <= daysInMonth; j++) {
                    daysArray.push(j);
                }
                this.blankdays = blankdaysArray;
                this.no_of_days = daysArray;
            },
        };
    }

</script>
<script  src='https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js'></script>
<script  src="https://cdn.datatables.net/v/dt/dt-1.11.4/datatables.min.js"></script>
<script >
    $(document).ready(function () {
        $('#datatable').DataTable();
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.1/dist/chart.min.js"></script>