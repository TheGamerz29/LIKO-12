--- LIKO-12's kernel V0.0.1 by Rami Sabbagh.
-- It's the bridge into the modern LIKO-12 I imagine.

--- The peripherals objects passed by the LIKO-12's VM.
local peripherals = ...

--- Deep copies a table.
local function deepCopy(table)
    local copy = {}

    for key, value in pairs(table) do
        if type(value) == "table" then
            if (value ~= table) then
                copy[key] = deepCopy(value)
            end
        else
            copy[key] = value
        end
    end

    return copy
end

-- Backup the initial Lua globals for usage with LIKO-12's containers.
local initialLuaGlobals = deepCopy(_G)

-- Restore some basic standard Lua functions.

--NOTE: This would work for now, but later it would need a paths resolver.
--TODO: loadfile read the code from standard input if no filename is given!
loadfile = peripherals.HDD.load

function dofile(path, ...)
    local chunk, err = loadfile(path)
    if not chunk then return error(tostring(err)) end
    local returnValues = {pcall(chunk, ...)}
    if not returnValues[1] then return error(tostring(returnValues[2])) end
    return select(2, unpack(returnValues))
end

dofile("kernel/package.lua")