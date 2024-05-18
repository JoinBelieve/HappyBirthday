package com.example.happybirthday

import org.junit.Test
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PropertyTest {

    open class SmartDevice(val name: String, val category: String) {
        var deviceStatus = "online"
            protected set


        open val deviceType = "unknown"

        open fun turnOn() {
            deviceStatus = "on"
        }

        open fun turnOff() {
            deviceStatus = "off"
        }

        fun printDeviceInfo() {
            println("Device name: $name,category: $category, type: $deviceType, status: $deviceStatus")
        }

    }

    class SmartTvDevice(deviceName: String, deviceCategory: String) :
        SmartDevice(name = deviceName, category = deviceCategory) {
        override val deviceType = "Smart TV"

        private var speakerVolume by RangeRegulator(initialValue = 2, maxValue = 100, minValue = 0)

        private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

        fun increaseSpeakerVolume() {
            speakerVolume++
            println("Speaker volume increased to $speakerVolume")
        }

        fun decreaseVolume() {
            speakerVolume--
            println("Speaker volume decreased to $speakerVolume")
        }

        fun previousChannel() {
            channelNumber--
            println("Channel number decreased to $channelNumber")
        }

        fun nextChannel() {
            channelNumber++
            println("Channel number increased to $channelNumber")
        }

        override fun turnOff() {
            super.turnOff()
            println("$name turned off")
        }

        override fun turnOn() {
            super.turnOn()
            println(
                "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                        "set to $channelNumber."
            )
        }
    }

    class SmartLightDevice(deviceName: String, deviceCategory: String) :
        SmartDevice(name = deviceName, category = deviceCategory) {
        override val deviceType: String = "Smart Light"

        private var brightnessLevel by RangeRegulator(
            initialValue = 0,
            minValue = 0,
            maxValue = 100
        )

        fun increaseBrightness() {
            brightnessLevel++
            println("Brightness increased to $brightnessLevel")
        }

        fun decreaseBrightness() {
            brightnessLevel--
            println("Brightness decreased to $brightnessLevel")
        }

        override fun turnOff() {
            super.turnOff()
            brightnessLevel = 0
            println("Smart Light turned off")
        }

        override fun turnOn() {
            super.turnOn()
            brightnessLevel = 2
            println("$name turned on. The brightness level is $brightnessLevel.")
        }


    }

    class SmartHome(
        val smartTvDevice: SmartTvDevice,
        val smartLightDevice: SmartLightDevice
    ) {


        var deviceTurnOnCount = 0
            private set

        fun turnOnTv() {
            deviceTurnOnCount++
            smartTvDevice.turnOn()
        }

        fun turnOffTv() {
            deviceTurnOnCount--
            smartTvDevice.turnOff()
        }

        fun increaseTvVolume() {
            smartTvDevice.increaseSpeakerVolume()
        }

        fun decreaseTvVolume() {
            smartTvDevice.decreaseVolume()
        }

        fun changeTvChannelToPrevious() {
            smartTvDevice.previousChannel()
        }

        fun printSmartTvInfo() {
            smartTvDevice.printDeviceInfo()
        }

        fun printSmartLightInfo() {
            smartLightDevice.printDeviceInfo()
        }

        fun decreaseLightBrightness() {
            smartLightDevice.decreaseBrightness()
        }

        fun changeTvChannelToNext() {
            smartTvDevice.nextChannel()
        }

        fun turnOnLight() {
            deviceTurnOnCount++
            smartLightDevice.turnOn()
        }

        fun turnOffLight() {
            deviceTurnOnCount--
            smartLightDevice.turnOff()
        }

        fun increaseLightBrightness() {
            smartLightDevice.increaseBrightness()
        }

        fun turnOffAllDevices() {
            turnOffTv()
            turnOffLight()
        }

    }

    class RangeRegulator(
        initialValue: Int,
        private val minValue: Int,
        private val maxValue: Int
    ) : ReadWriteProperty<Any?, Int> {

        var fieldData = initialValue
        override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
            return fieldData
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
            if (value in minValue..maxValue) {
                fieldData = value
            }
        }

    }

//    fun trick() {
//        println("No treats!")
//    }

    @Test
    fun main() {
//        var smartDevice = SmartTvDevice("Android TV", "Entertainment")
////        smartDevice.turnOn()
//        var smartDevice2 = SmartLightDevice(deviceName = "Google Light", deviceCategory = "Utility")
////        smartDevice.turnOn()
//        val homeDevice = SmartHome(smartDevice, smartDevice2)
//        homeDevice.turnOnTv()
//        homeDevice.turnOnLight()
//        homeDevice.printSmartTvInfo()
//        homeDevice.printSmartLightInfo()
//        homeDevice.increaseTvVolume()
//        homeDevice.decreaseTvVolume()
//        homeDevice.changeTvChannelToPrevious()
//        homeDevice.changeTvChannelToNext()
//        homeDevice.turnOffAllDevices()
//        homeDevice.increaseLightBrightness()
//        homeDevice.decreaseLightBrightness()
//        val trickFunction = trick
//        trick()
//        trickFunction()
//        treat()

        val coins: (Int) -> String = { quantity ->
            "$quantity quarters"
        }

        val cupcake: (Int) -> String = { quantity ->
            "Have a cupcake ! "
        }

        val treatFunction = trickOrTreat(false) { "$it quarters" }
        val trickFunction = trickOrTreat(true, null)
//        treatFunction()
//        trickFunction()
        repeat(4) {
            treatFunction()
        }
        trickFunction()
    }

    fun trickOrTreat(isTrick: Boolean, extraTreat: ((Int) -> String)?): () -> Unit {
        if (isTrick) {
            return trick
        } else {
            extraTreat?.let {
                println(it(5))
            }
            return treat
        }
    }

    val trick = {
        println("No treats!")
    }

    val treat = {
        println("Have a treat!")
    }

}