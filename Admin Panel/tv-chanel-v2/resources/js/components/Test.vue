<template>
<div>
    <b-button @click="$bvModal.show(itemId)">
        view
    </b-button>

    <b-modal v-if="type=='product'" :id="itemId" :size="size" :title="item.name">

        <b-card >

            <b-row  class="img">
                <b-col v-if="item.image">
                    
                    <div class="img-holder"> <b-img right
                     :src="item.image" 
                     alt="Right image" 
                      fluid-grow
                     
                    class=mb-2>
                    </b-img></div>
                  

                </b-col>
                <b-col>
                       <b-row class="mb-2"  >
                        <b-col v-if="item.name" class="label">Chanel Name:</b-col>
                        <b-col>
                            <b-card-text>
                                 {{item.name}}
                            </b-card-text>
                        </b-col>
                       
                    </b-row>
                    <b-row class="mb-2"  >
                        <b-col v-if="item.description" class="label">Description:</b-col>
                        <b-col>
                            <b-card-text>
                                <div v-html="item.description" class="description"></div>
                            </b-card-text>
                        </b-col>
                       
                    </b-row>

                    <b-row class="mb-2" >
                        <b-col v-if="item.category" class="label">Category:</b-col>
                        <b-col>
                            <b-card-text> {{item.category.name}}</b-card-text>
                        </b-col>
                    </b-row>

                    <b-row class="mb-2" >
                        <b-col v-if="item.url" class="label">Url link</b-col>
                        <b-col> 
                            <div class="link-box">
                                <a :href="item.url" class="card-link">{{item.url}}</a>
                            </div>
                         </b-col>
                    </b-row>

                    <b-row  class="mb-2">
                        <b-col v-if="item.url_type.name" class="label">Url type</b-col>
                        <b-col>
                            <b-card-text> {{item.url_type.name}}</b-card-text>
                        </b-col>
                    </b-row>
                    
                </b-col>
               
            </b-row>

        </b-card>

    </b-modal>


     <b-modal  v-if="type=='category'" :id="itemId" :size="size" :title="item.name">

        <b-card>

            <b-row class="img" >
                <b-col class="img-holder">

                    <b-img right :src="item.image" alt="Right image" fluid fluid-grow class=mb-2></b-img>

                </b-col>
                <b-col>
                    
                    <b-row class="mb-2" >
                        <b-col class="label">name</b-col>
                        <b-col>
                            <b-card-text> {{item.name}}</b-card-text>
                        </b-col>
                    </b-row>
                  

                  

                    

                    <b-row class="mb-2" >
                        <b-col class="label">Category type</b-col>
                        <b-col>
                            <b-card-text v-if="item.category_type==0"> locked</b-card-text>
                            <b-card-text v-else > unlocked</b-card-text>
                        </b-col>
                    </b-row>


                </b-col>
            </b-row>

        </b-card>

    </b-modal>

</div>
</template>

<script>
export default {
    props: ['item', 'size', 'type'],

    data() {
        return {

            itemId: 'a' + this.item.id,
        }
    },

}
</script>

<style>

.label {
    font-weight: 900;
    
}

 .img-holder {
     width: 400px;
     height:auto;
     z-index: 6;
 }

 .link-box {
       max-width: 200px;
    height: auto;
 }


@media only screen and (max-width:600px) {
    .img {
        display: block;
    }
    /* .description{
    max-width: 100%;
} */
}
</style>
