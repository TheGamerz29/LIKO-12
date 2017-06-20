--Enter a specifiec directory/path/drive
local args = {...} --Get the arguments passed to this program
if #args < 1 then color(8) print("Must provide the path") return end
local tar = table.concat(args," ") --The path may include whitespaces
local term = require("C://terminal")

tar = term.resolve(tar)

local ok, err = pcall(term.setpath,tar)
if not ok then
  color(8) print(tostring(err))
end