<template>
<div>
  <b-button   @click="$bvModal.show(item.id)" >View</b-button>

<b-modal :id="item.id" :size="size" :title="item.name">
   <div>
         <b-card
         img-height="400"
    overlay
    :img-src="item.image"
    img-alt="Card Image"
    
    
  >
   
  </b-card>

  <b-card-text class="slider-text">
      Some quick example text to build on the card and make up the bulk of the card's content.
    </b-card-text>
  </div>

  
</b-modal>



</div>
</template>

<script>
export default {
  props: ['item','size'],
  watch: {
    
    
  },


   data() {
      return {
       
         
      }
    },
     
}
</script>


 