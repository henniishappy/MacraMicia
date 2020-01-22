function validateDateTime() {
    var date = document.getElementById("date").value;
    var time = document.getElementById("time").value;
    var nowDateTime = new Date();

    var courseDateTime = new Date(date + "T" + time + ":00")
    if (courseDateTime > nowDateTime) {
        return true;
    }
    else {
        alert("Please select a date in the future instead.")
        return false;
    }
}