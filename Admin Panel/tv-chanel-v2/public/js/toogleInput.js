function toogleEdit(element) {
    var test = document.getElementById(element);
    
     
    if (test.hasAttribute('readonly')) {
        test.removeAttribute('readonly')
         test.focus();
  } else {
        test.setAttribute('readonly', 'readonly');
       

  }
 
}