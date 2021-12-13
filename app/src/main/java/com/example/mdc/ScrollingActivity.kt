package com.example.mdc

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.URLUtil
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mdc.databinding.ActivityScrollingBinding
import com.google.android.material.bottomappbar.BottomAppBar
//snackbar:mostrar mensajes y ejecutar alguna accion se diseña en kotlin
class ScrollingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScrollingBinding//Codigo ma claro y acceder solo a la raiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_scrolling)
        binding= ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)//root es la raiz de la vista es decir el que se genera solo el xml
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
//            findViewById<TextView>(R.id.tvMessageAlt).setText("Locoflaxeee")
//            if(findViewById<BottomAppBar>(R.id.bottom_app_bar).fabAlignmentMode== BottomAppBar.FAB_ALIGNMENT_MODE_CENTER){///Si el floating action buton está en el centro
//                findViewById<BottomAppBar>(R.id.bottom_app_bar).fabAlignmentMode== BottomAppBar.FAB_ALIGNMENT_MODE_END
//            }else{
//                findViewById<BottomAppBar>(R.id.bottom_app_bar).fabAlignmentMode== BottomAppBar.FAB_ALIGNMENT_MODE_END
//
//            }
//        }
        binding.fab.setOnClickListener {

            if(binding.bottomAppBar.fabAlignmentMode== BottomAppBar.FAB_ALIGNMENT_MODE_CENTER){///Si el floating action buton está en el centro
                binding.bottomAppBar.fabAlignmentMode== BottomAppBar.FAB_ALIGNMENT_MODE_END
            }else{
                binding.bottomAppBar.fabAlignmentMode== BottomAppBar.FAB_ALIGNMENT_MODE_END

            }
        }
//snackbar diseño en kotlin como un toast
binding.bottomAppBar.setNavigationOnClickListener({
    Snackbar.make(binding.root,R.string.message_action_succes,Snackbar.LENGTH_LONG)
        .setAnchorView(binding.fab)//donde va estar arriba del floating
        .show()
})
//Con el bindin solo se puede acceder a los elemntos que
        //tengan un id en la raiz si no se pone el id en el include
        binding.content.btnSkip.setOnClickListener{binding.content.cvAd.visibility= View.GONE}
   binding.content.btnBuy.setOnClickListener {
       Snackbar.make(it,R.string.card_buying,Snackbar.LENGTH_LONG)
           .setAnchorView(binding.fab)
           .setAction(R.string.card_to_go,{
               Toast.makeText(this,R.string.card_historial,Toast.LENGTH_SHORT).show()
           })
           .show()
   }
      //glide para caragra imagenes desde internet

            glide("https://i.ytimg.com/vi/uNhAHzUpsXQ/hqdefault.jpg")//url que debe cargar

binding.content.cbEnablePass.setOnClickListener {
    binding.content.tilPassword.isEnabled = !binding.content.tilPassword.isEnabled

}

binding.content.eturl.setOnFocusChangeListener { view, focused ->
var errorStr:String?=null
    var url=binding.content.eturl.text.toString()
    if(!focused){
        if (url.isEmpty()){
            errorStr = getString(R.string.card_required)
        }else if(URLUtil.isValidUrl(url)){
            glide(url)//url que debe cargar
        }else{
            errorStr=getString(R.string.card_invalid_url)
        }


   /*     Snackbar.make(binding.root,"Foco perdido",Snackbar.LENGTH_SHORT)
            .setAnchorView(binding.content.tilUrl)
            .show()*/
    }
    binding.content.tilUrl.error=errorStr
}//Detectar cuando se ha dejado de escribir el el EditText
        binding.content.toogleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            binding.content.root.setBackgroundColor(
              when(checkedId){
                  R.id.btnRed-> Color.RED
                  R.id.btnBlue-> Color.BLUE
                  else ->Color.GREEN///siempre tiene que haber un caso por default
              }
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }
fun glide(url:String="https://i.ytimg.com/vi/uNhAHzUpsXQ/hqdefault.jpg"){
    Glide.with(this)//contexto
        .load(url)//url que debe cargar
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .into(binding.content.imgCover)//donde
}
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}