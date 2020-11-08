--- HDD peripheral V2.0.0
-- by Rami Sabbagh (@Rami-Sabbagh)

local class = require("libraries.middleclass")

local HDD = class("peripherals.HDD")

HDD.static.defaultProperties = {
    rootPath = "/storage/main/", --must end with a trailing /
    usageLimit = 20*1024*1024
}

function HDD:initialize(properties)
    properties = properties or {}
    --So 'nil' fields fallback to the default values.
    setmetatable(properties, {__index = self.defaultProperties})

    self.rootPath = properties.rootPath
    self.usageLimit = properties.usageLimit
end

return HDD