package apps.r2b.gymtrainer.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Routine(@SerializedName("imageResId")  @Expose var imageResId: Int = 0,
                   @SerializedName("name")        @Expose var name: String,
                   @SerializedName("enabled")     @Expose var enabled: Boolean = false,
                   @SerializedName("weight")      @Expose var weight: Int = 0,
                   @SerializedName("series")      @Expose var series: Int = 2,
                   @SerializedName("repetitions") @Expose var repetitions: Int = 10): Parcelable {

    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString(),
        source.readInt() == 1,
        source.readInt(),
        source.readInt(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(imageResId)
        writeString(name)
        if (enabled) {
            writeInt(1)
        } else {
            writeInt(0)
        }
        writeInt(weight)
        writeInt(series)
        writeInt(repetitions)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Routine> = object : Parcelable.Creator<Routine> {
            override fun createFromParcel(source: Parcel): Routine = Routine(source)
            override fun newArray(size: Int): Array<Routine?> = arrayOfNulls(size)
        }
    }

}

