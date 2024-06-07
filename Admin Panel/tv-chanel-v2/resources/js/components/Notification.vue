<template>
   <div>
 <b-button   @click="$bvModal.show(itemId)" >View</b-button>

 <b-modal :id="itemId" :size="size" :title="item.name">
   <div>
         <b-card
         img-height="100"
    
    :img-src="item.image"
    img-alt="Card Image"
         >
     <b-card-text class="slider-text">
     message: {{item.message}}
    </b-card-text>
  </b-card>


  </div>

  
</b-modal>
  </div>
 
</template>

<script>
    export default {
    props:  ['item', 'size'],
        data() {
        return {

            itemId: 'a' + this.item.id,
        }
    },
    }
</script>

 