 function generateToken() {

         var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

         var length = 12
         var result = ' ';
         var charactersLength = characters.length;
         for (let i = 0; i < length; i++) {
             result += characters.charAt(Math.floor(Math.random() * charactersLength));
         }

         document.getElementById("generateBox").value = result;

     }

     function copyToken() {


         $('#modal-default').modal('hide');
         $('#exampleModal').modal('show');
     }

     function applyToken() {
         var generateBox = document.getElementById("generateBox")
         var tokenBox = document.getElementById("tokenBox")
         var result = generateBox.value;

         document.getElementById("tokenBox").value = generateBox.value;
         generateBox.value = ""
         $('#exampleModal').modal('hide');
}
     
 