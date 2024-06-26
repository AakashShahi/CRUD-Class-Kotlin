package com.example.crud_34a.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crud_34a.model.ProductModel
import com.example.crud_34a.repository.ProductRepository

class ProductViewModel(val repository: ProductRepository) : ViewModel() {

    fun deleteProducts(id:String,callback: (Boolean, String?) -> Unit){
        repository.deleteProducts(id,callback)
    }

    fun deleteImage(imageName:String?,callback: (Boolean, String?) -> Unit){
        repository.deleteImage(imageName,callback)
    }

    fun updateProducts(id:String,data:MutableMap<String,Any>?,callback: (Boolean, String?) -> Unit){
        repository.updateProducts(id,data,callback)

    }


    fun uploadImages(src:String,imageUri: Uri, callback: (Boolean, String?, String?) -> Unit) {
        repository.uploadImages(src,imageUri) { success, imageUrl,message ->
            callback(success, imageUrl,message)
        }
    }
    fun addProducts(productModel: ProductModel, callback: (Boolean, String?) -> Unit){
        repository.addProducts(productModel,callback)
    }

    var _productList = MutableLiveData<List<ProductModel>?>()

    var productList = MutableLiveData<List<ProductModel>?>()
                get()=_productList//getter


    var _loadingState=MutableLiveData<Boolean>()
    var loadingState=MutableLiveData<Boolean>()
        get()=_loadingState

    fun fetchAllProducts(){
        _loadingState.value=true
        repository.getAllProducts { products, success, meassage ->
            if(products!=null){
                _loadingState.value=false
                _productList.value=products
            }
        }
    }
}