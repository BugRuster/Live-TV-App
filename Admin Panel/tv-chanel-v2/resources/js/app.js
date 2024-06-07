require('./bootstrap');
require('./test.scss')
import { BootstrapVue } from 'bootstrap-vue'

var Vue = require('vue')
 

Vue.component("modal-el", () => import("./components/Test.vue"));
Vue.component("modal-slide", () => import("./components/Slider.vue"));
Vue.component("modal-notification", () => import("./components/Notification.vue"));
Vue.use(BootstrapVue)


const app = new Vue({
  el: '#app',
   data: {
    exampleModalShowing: false,
  },
 
});