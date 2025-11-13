package com.veterinaria.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.veterinaria.data.model.Mascota




@Composable
fun MascotaList(lista: List<Mascota>, onPetClick: (Int) -> Unit, modifier: Modifier = Modifier){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items = lista, key = { it.id }) { pet ->
            PetCard( mascota = pet, onClick = { onPetClick(pet.id) })
        }
    }

}