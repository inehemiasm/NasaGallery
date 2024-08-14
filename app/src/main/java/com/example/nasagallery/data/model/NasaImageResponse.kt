package com.example.nasagallery.data.model

data class NasaImagesResponse(
    val collection: CollectionData,
)

data class CollectionData(
    val version: String,
    val href: String,
    val items: List<Items>,
    val metadata: Metadata,
) {
    fun toUiImages(): List<NasaImageUIModel> {
        return items.map { item ->
            // TODO; Move hardcoded string to string resources
            val thumbnailUrl = item.links?.firstOrNull()?.href ?: ""
            NasaImageUIModel(
                title = item.data.firstOrNull()?.title ?: "No Title",
                thumbnailUrl = thumbnailUrl,
                description = item.data.firstOrNull()?.description ?: "No Description",
                photographer = item.data.firstOrNull()?.photographer ?: "Unknown Photographer",
                location = item.data.firstOrNull()?.center ?: "Unknown Location",
            )
        }
    }
}

data class Items(
    val data: List<ItemData>,
    val links: List<ItemLink>?,
)

data class ItemData(
    val title: String,
    val description: String,
    val date_created: String,
    val center: String,
    val photographer: String,
)

data class ItemLink(
    val href: String,
    val rel: String,
)

data class Metadata(
    val total_hits: Int,
)

data class NasaImageUIModel(
    val title: String,
    val thumbnailUrl: String,
    val description: String,
    val photographer: String,
    val location: String,
)
